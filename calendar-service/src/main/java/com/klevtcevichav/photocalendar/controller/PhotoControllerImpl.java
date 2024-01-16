package com.klevtcevichav.photocalendar.controller;

import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import com.klevtcevichav.photocalendar.dto.request.AddPhotoRequestDTO;
import com.klevtcevichav.photocalendar.dto.response.PhotoResponseDTO;
import com.klevtcevichav.photocalendar.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/calendar/photos")
public class PhotoControllerImpl implements PhotoController{

    private final PhotoService photoService;

    @Override
    @PostMapping
    public ResponseEntity<SimpleResponseDTO> addPhoto(AddPhotoRequestDTO addPhotoRequestDTO) {
        SimpleResponseDTO simpleResponseDTO = photoService.addPhoto(addPhotoRequestDTO);

        return new ResponseEntity<>(simpleResponseDTO, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{photoId}")
    public ResponseEntity<SimpleResponseDTO> removePhoto(@PathVariable Long photoId) {
        SimpleResponseDTO simpleResponseDTO = photoService.removePhoto(photoId);

        return new ResponseEntity<>(simpleResponseDTO, HttpStatus.NO_CONTENT);
    }

    @Override
    @GetMapping("/{photoId}")
    public ResponseEntity<PhotoResponseDTO> getPhoto(@PathVariable Long photoId) {
        PhotoResponseDTO photoResponseDTO = photoService.getPhoto(photoId);

        return ResponseEntity.ok(photoResponseDTO);
    }
}
