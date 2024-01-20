package com.klevtcevichav.photocalendar.controller;

import com.klevtcevichav.photocalendar.auth.dto.request.UserRequestDTO;
import com.klevtcevichav.photocalendar.auth.dto.request.UserUpdatePasswordDTO;
import com.klevtcevichav.photocalendar.auth.dto.request.UserUpdateRequestDTO;
import com.klevtcevichav.photocalendar.auth.dto.response.UserResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import org.springframework.http.ResponseEntity;

public interface UserController {

    ResponseEntity<SimpleResponseDTO> registrationUser(UserRequestDTO userRequestDTO);
    ResponseEntity<UserResponseDTO> updateUser(UserUpdateRequestDTO userUpdateRequestDTO);
    ResponseEntity<SimpleResponseDTO> delete(Long id);
    ResponseEntity<UserResponseDTO> getUserById(Long id);
    ResponseEntity<SimpleResponseDTO> updatePassword(UserUpdatePasswordDTO userUpdatePasswordDTO);
}
