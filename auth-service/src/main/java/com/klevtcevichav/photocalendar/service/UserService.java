package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import com.klevtcevichav.photocalendar.dto.request.UserRequestDTO;
import com.klevtcevichav.photocalendar.dto.request.UserUpdatePasswordDTO;
import com.klevtcevichav.photocalendar.dto.request.UserUpdateRequestDTO;
import com.klevtcevichav.photocalendar.dto.response.UserResponseDTO;

public interface UserService {

    SimpleResponseDTO registrationUser(UserRequestDTO userRequestDTO);
    UserResponseDTO updateUser(UserUpdateRequestDTO userUpdateRequestDTO);
    SimpleResponseDTO delete(Long id);
    UserResponseDTO getUserById(Long id);

    SimpleResponseDTO updatePassword(UserUpdatePasswordDTO userUpdatePasswordDTO);

    String test();

}
