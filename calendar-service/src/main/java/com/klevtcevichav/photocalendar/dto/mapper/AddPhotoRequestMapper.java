package com.klevtcevichav.photocalendar.dto.mapper;

import com.klevtcevichav.photocalendar.dto.request.AddPhotoRequestDTO;
import com.klevtcevichav.photocalendar.entity.Photo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddPhotoRequestMapper {

    Photo addPhotoRequestDTOToPhoto(AddPhotoRequestDTO addPhotoRequestDTO);
}
