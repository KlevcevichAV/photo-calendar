package com.klevtcevichav.photocalendar.dto.mapper;

import com.klevtcevichav.photocalendar.calendar.dto.response.PhotoResponseDTO;
import com.klevtcevichav.photocalendar.entity.Photo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhotoResponseMapper {

    PhotoResponseDTO photoToPhotoResponseDTO(Photo photo);
}
