package com.klevtcevichav.photocalendar.controller;

import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import com.klevtcevichav.photocalendar.dto.request.UserRequestDTO;
import com.klevtcevichav.photocalendar.dto.request.UserUpdatePasswordDTO;
import com.klevtcevichav.photocalendar.dto.request.UserUpdateRequestDTO;
import com.klevtcevichav.photocalendar.dto.response.UserResponseDTO;
import org.springframework.http.ResponseEntity;

public interface UserController {

    ResponseEntity<SimpleResponseDTO> registerUser(UserRequestDTO userRequestDTO);
    ResponseEntity<UserResponseDTO> updateUser(UserUpdateRequestDTO userUpdateRequestDTO);
    ResponseEntity<SimpleResponseDTO> delete(Long id);
    ResponseEntity<UserResponseDTO> getUserById(Long id);
    ResponseEntity<SimpleResponseDTO> updatePassword(UserUpdatePasswordDTO userUpdatePasswordDTO);
}
