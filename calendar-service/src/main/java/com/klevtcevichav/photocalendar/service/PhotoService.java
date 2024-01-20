package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.calendar.dto.request.AddPhotoRequestDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.PhotoResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;

public interface PhotoService {

    SimpleResponseDTO addPhoto(AddPhotoRequestDTO addPhotoRequestDTO);
    SimpleResponseDTO removePhoto(Long photoId);
    PhotoResponseDTO getPhoto(Long photoId);
}
