package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.account.client.AccountClientApi;
import com.klevtcevichav.photocalendar.account.dto.response.AccountResponseDTO;
import com.klevtcevichav.photocalendar.calendar.dto.request.AddPhotoRequestDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.PhotoResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import com.klevtcevichav.photocalendar.core.exception.NotFoundException;
import com.klevtcevichav.photocalendar.dto.mapper.AddPhotoRequestMapper;
import com.klevtcevichav.photocalendar.dto.mapper.PhotoResponseMapper;
import com.klevtcevichav.photocalendar.entity.Photo;
import com.klevtcevichav.photocalendar.exception.CalendarBusinessException;
import com.klevtcevichav.photocalendar.repository.PhotoRepository;
import com.klevtcevichav.photocalendar.s3.S3Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class PhotoServiceImpl implements PhotoService{

    private final S3Service s3Service;
    private final PhotoRepository photoRepository;
    private final AddPhotoRequestMapper addPhotoRequestMapper;
    private final PhotoResponseMapper photoResponseMapper;
    private final AccountClientApi accountClientApi;

    @Override
    @Transactional
    public SimpleResponseDTO addPhoto(AddPhotoRequestDTO addPhotoRequestDTO) {

        log.info("Start adding photo to account with id:{}. Photo:{}", addPhotoRequestDTO.getAccountId(), addPhotoRequestDTO);
        getAccount(addPhotoRequestDTO.getAccountId());

        Photo photo = addPhotoRequestMapper.addPhotoRequestDTOToPhoto(addPhotoRequestDTO);
        photo.setKey(UUID.randomUUID());

        log.info("Call S3 service for load photo to S3 with key: {}", photo.getKey());
        s3Service.putObject(photo.getKey().toString(), addPhotoRequestDTO.getPhoto());
        Photo savedPhoto = photoRepository.save(photo);

        log.info("Photo added: {}", savedPhoto);
        return new SimpleResponseDTO();
    }

    @Override
    @Transactional
    public SimpleResponseDTO removePhoto(Long id, Long accountId) {

        log.info("Start deleting photo with id: {}", id);
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found photo by id: " + id));
        AccountResponseDTO accountResponseDTO = getAccount(accountId);
        if (!photo.getAccountId().equals(accountResponseDTO.getId())) {
            throw new CalendarBusinessException("You can't delete photo with id: " + id);
        }
        photo.setDateOfDelete(LocalDateTime.now());

        photoRepository.save(photo);

        log.info("Photo with id: {} deleted", id);
        return new SimpleResponseDTO();
    }

    @Override
    public PhotoResponseDTO getPhoto(Long id, Long accountId) {

        log.info("Start finding photo with id: {}", id);
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found photo by id: " + id));
        AccountResponseDTO accountResponseDTO = getAccount(accountId);

        if (photo.getAccountId().equals(accountResponseDTO.getId())) {
            throw new CalendarBusinessException("You can't getting photo with id: " + id);
        }

        PhotoResponseDTO photoResponseDTO = photoResponseMapper.photoToPhotoResponseDTO(photo);
        log.info("Call S3 service for getting photo from S3 with key: {}", photo.getKey());
        photoResponseDTO.setPhoto(s3Service.getObject(photo.getKey().toString()));

        log.info("Photo with id:{} found:{}", id, photoResponseDTO);
        return photoResponseDTO;
    }

    private AccountResponseDTO getAccount(Long accountId) {

        log.info("Start calling Account service for finding account with id: {}", accountId);
        ResponseEntity<AccountResponseDTO> accountResponseEntity = accountClientApi.getAccount(accountId);
        if (!HttpStatus.OK.equals(accountResponseEntity.getStatusCode()) || Objects.isNull(accountResponseEntity.getBody())) {
            throw new NotFoundException("Can not find account by id: " + accountId);
        }
        return accountResponseEntity.getBody();
    }

}
