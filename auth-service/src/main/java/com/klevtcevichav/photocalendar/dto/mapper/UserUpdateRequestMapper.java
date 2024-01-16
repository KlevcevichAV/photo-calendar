package com.klevtcevichav.photocalendar.dto.mapper;

import com.klevtcevichav.photocalendar.dto.request.UserUpdateRequestDTO;
import com.klevtcevichav.photocalendar.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserUpdateRequestMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDTO(UserUpdateRequestDTO userUpdateRequestDTO, @MappingTarget User user);
}
