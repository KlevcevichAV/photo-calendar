package com.klevtcevichav.photocalendar.calendar.client;

import com.klevtcevichav.photocalendar.calendar.dto.request.AddPhotoRequestDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.PhotoResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient
public interface PhotoClientApi {

    @PostMapping
    ResponseEntity<SimpleResponseDTO> addPhoto(AddPhotoRequestDTO addPhotoRequestDTO);

    @DeleteMapping("/{photoId}")
    ResponseEntity<SimpleResponseDTO> removePhoto(@PathVariable Long photoId);

    @GetMapping("/{photoId}")
    ResponseEntity<PhotoResponseDTO> getPhoto(@PathVariable Long photoId);
}
