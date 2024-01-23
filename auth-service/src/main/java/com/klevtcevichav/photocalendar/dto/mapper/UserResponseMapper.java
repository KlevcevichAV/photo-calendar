package com.klevtcevichav.photocalendar.dto.mapper;

import com.klevtcevichav.photocalendar.auth.dto.response.UserResponseDTO;
import com.klevtcevichav.photocalendar.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    UserResponseDTO userToUserResponseDTO(UserProfile userProfile);
}
