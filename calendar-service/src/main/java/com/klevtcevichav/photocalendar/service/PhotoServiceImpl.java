package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.calendar.dto.request.AddPhotoRequestDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.PhotoResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import com.klevtcevichav.photocalendar.core.exception.NotFoundException;
import com.klevtcevichav.photocalendar.dto.mapper.AddPhotoRequestMapper;
import com.klevtcevichav.photocalendar.dto.mapper.PhotoResponseMapper;
import com.klevtcevichav.photocalendar.entity.Photo;
import com.klevtcevichav.photocalendar.repository.PhotoRepository;
import com.klevtcevichav.photocalendar.s3.S3Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class PhotoServiceImpl implements PhotoService{

    private final S3Service s3Service;
    private final PhotoRepository photoRepository;
    private final AddPhotoRequestMapper addPhotoRequestMapper;
    private final PhotoResponseMapper photoResponseMapper;

    @Override
    public SimpleResponseDTO addPhoto(AddPhotoRequestDTO addPhotoRequestDTO) {

        log.info("Start adding photo to account with id:{}. Photo:{}", addPhotoRequestDTO.getAccountId(), addPhotoRequestDTO);
        Photo photo = addPhotoRequestMapper.addPhotoRequestDTOToPhoto(addPhotoRequestDTO);
        photo.setKey(UUID.randomUUID());

        log.info("Call S3 service for load photo to S3 with key: {}", photo.getKey());
        s3Service.putObject(photo.getKey().toString(), addPhotoRequestDTO.getPhoto());
        Photo savedPhoto = photoRepository.save(photo);

        log.info("Photo added: {}", savedPhoto);
        return new SimpleResponseDTO();
    }

    @Override
    public SimpleResponseDTO removePhoto(Long id) {

        log.info("Start deleting photo with id: {}", id);
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found photo by id: " + id));

        photo.setDateOfDelete(LocalDateTime.now());

        photoRepository.save(photo);

        log.info("Photo with id: {} deleted", id);
        return new SimpleResponseDTO();
    }

    @Override
    public PhotoResponseDTO getPhoto(Long id) {

        log.info("Start finding photo with id: {}", id);
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found photo by id: " + id));

        PhotoResponseDTO photoResponseDTO = photoResponseMapper.photoToPhotoResponseDTO(photo);
        log.info("Call S3 service for getting photo from S3 with key: {}", photo.getKey());
        photoResponseDTO.setPhoto(s3Service.getObject(photo.getKey().toString()));

        log.info("Photo with id:{} found:{}", id, photoResponseDTO);
        return photoResponseDTO;
    }

}
