package com.klevtcevichav.photocalendar.dto.mapper;

import com.klevtcevichav.photocalendar.auth.dto.request.UserRequestDTO;
import com.klevtcevichav.photocalendar.entity.AbstractUser;
import com.klevtcevichav.photocalendar.entity.UserProfile;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {
    @BeanMapping(builder = @Builder( disableBuilder = true ))
    @SubclassMapping(source = UserRequestDTO.class, target = AbstractUser.class)
    UserProfile userRequestDTOToUser(UserRequestDTO userRequestDTO);
}
