package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.calendar.dto.request.AddPhotoRequestDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.PhotoResponseDTO;

import java.io.IOException;

public interface PhotoService {

    PhotoResponseDTO addPhoto(AddPhotoRequestDTO addPhotoRequestDTO) throws IOException;
    void removePhoto(Long photoId, Long accountId);
    PhotoResponseDTO getPhoto(Long photoId, Long accountId);
}
