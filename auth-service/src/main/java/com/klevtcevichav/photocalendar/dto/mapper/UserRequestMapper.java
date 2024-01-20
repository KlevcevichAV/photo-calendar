package com.klevtcevichav.photocalendar.dto.mapper;

import com.klevtcevichav.photocalendar.auth.dto.request.UserRequestDTO;
import com.klevtcevichav.photocalendar.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {
    User userRequestDTOToUser(UserRequestDTO userRequestDTO);
}
