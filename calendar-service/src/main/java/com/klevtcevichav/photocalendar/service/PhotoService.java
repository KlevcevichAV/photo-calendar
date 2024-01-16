package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import com.klevtcevichav.photocalendar.dto.request.AddPhotoRequestDTO;
import com.klevtcevichav.photocalendar.dto.response.PhotoResponseDTO;

public interface PhotoService {

    SimpleResponseDTO addPhoto(AddPhotoRequestDTO addPhotoRequestDTO);
    SimpleResponseDTO removePhoto(Long photoId);
    PhotoResponseDTO getPhoto(Long photoId);
}
