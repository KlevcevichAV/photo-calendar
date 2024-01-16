package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import com.klevtcevichav.photocalendar.dto.mapper.AddPhotoRequestMapper;
import com.klevtcevichav.photocalendar.dto.mapper.PhotoResponseMapper;
import com.klevtcevichav.photocalendar.dto.request.AddPhotoRequestDTO;
import com.klevtcevichav.photocalendar.dto.response.PhotoResponseDTO;
import com.klevtcevichav.photocalendar.entity.Photo;
import com.klevtcevichav.photocalendar.repository.PhotoRepository;
import com.klevtcevichav.photocalendar.s3.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PhotoServiceImpl implements PhotoService{

    private final S3Service s3Service;
    private final PhotoRepository photoRepository;
    private final AddPhotoRequestMapper addPhotoRequestMapper;
    private final PhotoResponseMapper photoResponseMapper;

    @Override
    public SimpleResponseDTO addPhoto(AddPhotoRequestDTO addPhotoRequestDTO) {

        Photo photo = addPhotoRequestMapper.addPhotoRequestDTOToPhoto(addPhotoRequestDTO);
        photo.setKey(UUID.randomUUID());

        s3Service.putObject(photo.getKey().toString(), addPhotoRequestDTO.getPhoto());
        photoRepository.save(photo);

        return new SimpleResponseDTO();
    }

    @Override
    public SimpleResponseDTO removePhoto(Long id) {
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));

        photo.setDateOfDelete(LocalDateTime.now());

        photoRepository.save(photo);

        return new SimpleResponseDTO();
    }

    @Override
    public PhotoResponseDTO getPhoto(Long id) {
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));

        PhotoResponseDTO photoResponseDTO = photoResponseMapper.photoToPhotoResponseDTO(photo);
        photoResponseDTO.setPhoto(s3Service.getObject(photo.getKey().toString()));

        return photoResponseDTO;
    }

}
