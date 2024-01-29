package com.klevtcevichav.photocalendar.controller;

import com.klevtcevichav.photocalendar.calendar.client.PhotoClientApi;
import com.klevtcevichav.photocalendar.calendar.dto.request.AddPhotoRequestDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.PhotoResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import com.klevtcevichav.photocalendar.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/calendar/photos")
public class PhotoControllerImpl implements PhotoClientApi {

    private final PhotoService photoService;

    @Override
    @PostMapping
    public ResponseEntity<PhotoResponseDTO> addPhoto(AddPhotoRequestDTO addPhotoRequestDTO) throws IOException {
        PhotoResponseDTO photoResponseDTO = photoService.addPhoto(addPhotoRequestDTO);

        return new ResponseEntity<>(photoResponseDTO, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{photoId}")
    public ResponseEntity<SimpleResponseDTO> removePhoto(@PathVariable Long photoId, @RequestParam Long accountId) {
        photoService.removePhoto(photoId, accountId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{photoId}")
    public ResponseEntity<PhotoResponseDTO> getPhoto(@PathVariable Long photoId, @RequestParam Long accountId) {
        PhotoResponseDTO photoResponseDTO = photoService.getPhoto(photoId, accountId);

        return ResponseEntity.ok(photoResponseDTO);
    }
}
