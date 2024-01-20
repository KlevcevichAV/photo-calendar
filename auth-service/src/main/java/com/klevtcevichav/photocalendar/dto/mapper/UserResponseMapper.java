package com.klevtcevichav.photocalendar.dto.mapper;

import com.klevtcevichav.photocalendar.auth.dto.response.UserResponseDTO;
import com.klevtcevichav.photocalendar.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    UserResponseDTO userToUserResponseDTO(User user);
}
