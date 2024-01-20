package com.klevtcevichav.photocalendar.controller;

import com.klevtcevichav.photocalendar.calendar.dto.request.AddPhotoRequestDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.PhotoResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import org.springframework.http.ResponseEntity;

public interface PhotoController {

    ResponseEntity<SimpleResponseDTO> addPhoto(AddPhotoRequestDTO addPhotoRequestDTO);
    ResponseEntity<SimpleResponseDTO> removePhoto(Long photoId);
    ResponseEntity<PhotoResponseDTO> getPhoto(Long photoId);
}
