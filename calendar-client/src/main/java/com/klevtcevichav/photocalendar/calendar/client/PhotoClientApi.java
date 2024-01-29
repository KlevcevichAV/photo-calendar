package com.klevtcevichav.photocalendar.calendar.client;

import com.klevtcevichav.photocalendar.calendar.dto.request.AddPhotoRequestDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.PhotoResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@FeignClient("photo")
public interface PhotoClientApi {

    @PostMapping
    ResponseEntity<PhotoResponseDTO> addPhoto(AddPhotoRequestDTO addPhotoRequestDTO) throws IOException;

    @DeleteMapping("/{photoId}")
    ResponseEntity<SimpleResponseDTO> removePhoto(@PathVariable Long photoId, @RequestParam Long accountId);

    @GetMapping("/{photoId}")
    ResponseEntity<PhotoResponseDTO> getPhoto(@PathVariable Long photoId, @RequestParam Long accountId);
}
