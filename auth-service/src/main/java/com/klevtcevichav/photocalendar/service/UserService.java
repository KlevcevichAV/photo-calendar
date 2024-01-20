package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.auth.dto.request.UserRequestDTO;
import com.klevtcevichav.photocalendar.auth.dto.request.UserUpdatePasswordDTO;
import com.klevtcevichav.photocalendar.auth.dto.request.UserUpdateRequestDTO;
import com.klevtcevichav.photocalendar.auth.dto.response.UserResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;

public interface UserService {

    SimpleResponseDTO registrationUser(UserRequestDTO userRequestDTO);
    UserResponseDTO updateUser(UserUpdateRequestDTO userUpdateRequestDTO);
    SimpleResponseDTO delete(Long id);
    UserResponseDTO getUserById(Long id);

    SimpleResponseDTO updatePassword(UserUpdatePasswordDTO userUpdatePasswordDTO);

}
