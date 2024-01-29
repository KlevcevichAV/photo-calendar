package com.klevtcevichav.photocalendar.dto.mapper;

import com.klevtcevichav.photocalendar.calendar.dto.response.PhotoResponseDTO;
import com.klevtcevichav.photocalendar.core.entity.FullAbstractEntity;
import com.klevtcevichav.photocalendar.entity.Photo;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring")
public interface PhotoResponseMapper {
    @BeanMapping(builder = @Builder( disableBuilder = true ))
    @SubclassMapping(source = FullAbstractEntity.class, target = PhotoResponseDTO.class)
    PhotoResponseDTO photoToPhotoResponseDTO(Photo photo);
}
