package com.klevtcevichav.photocalendar.dto.mapper;

import com.klevtcevichav.photocalendar.auth.dto.request.UserRequestDTO;
import com.klevtcevichav.photocalendar.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {
    UserProfile userRequestDTOToUser(UserRequestDTO userRequestDTO);
}
