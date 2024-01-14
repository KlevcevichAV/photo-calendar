package com.klevtcevichav.photocalendar.dto.mapper;

import com.klevtcevichav.photocalendar.dto.request.UserUpdateRequestDTO;
import com.klevtcevichav.photocalendar.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserUpdateRequestMapper {

    User userUpdateRequestDTOToUser(UserUpdateRequestDTO userUpdateRequestDTO);
}
