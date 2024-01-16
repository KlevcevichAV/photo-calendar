package com.klevtcevichav.photocalendar.controller;

import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import com.klevtcevichav.photocalendar.dto.request.AddPhotoRequestDTO;
import com.klevtcevichav.photocalendar.dto.response.PhotoResponseDTO;
import org.springframework.http.ResponseEntity;

public interface PhotoController {

    ResponseEntity<SimpleResponseDTO> addPhoto(AddPhotoRequestDTO addPhotoRequestDTO);
    ResponseEntity<SimpleResponseDTO> removePhoto(Long photoId);
    ResponseEntity<PhotoResponseDTO> getPhoto(Long photoId);
}
