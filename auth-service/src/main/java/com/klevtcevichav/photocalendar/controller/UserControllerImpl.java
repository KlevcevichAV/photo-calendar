package com.klevtcevichav.photocalendar.controller;

import com.klevtcevichav.photocalendar.auth.client.UserClientApi;
import com.klevtcevichav.photocalendar.auth.dto.request.UserRequestDTO;
import com.klevtcevichav.photocalendar.auth.dto.request.UserUpdatePasswordDTO;
import com.klevtcevichav.photocalendar.auth.dto.request.UserUpdateRequestDTO;
import com.klevtcevichav.photocalendar.auth.dto.response.UserResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import com.klevtcevichav.photocalendar.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserControllerImpl implements UserClientApi {

    private final UserService userService;

    @Override
    @PostMapping("/registration")
    public ResponseEntity<SimpleResponseDTO> registrationUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {

        userService.registrationUser(userRequestDTO);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody @Valid UserUpdateRequestDTO userUpdateRequestDTO) {

        UserResponseDTO userResponseDTO = userService.updateUser(userUpdateRequestDTO);

        return ResponseEntity.ok(userResponseDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleResponseDTO> delete(@PathVariable("id") @Positive Long id) {

        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") Long id) {

        UserResponseDTO userResponseDTO = userService.getUserById(id);

        return ResponseEntity.ok(userResponseDTO);
    }

    @Override
    @PatchMapping("/{id}/change-password")
    public ResponseEntity<SimpleResponseDTO> updatePassword(@RequestBody @ Valid UserUpdatePasswordDTO userUpdatePasswordDTO) {

        userService.updatePassword(userUpdatePasswordDTO);

        return ResponseEntity.noContent().build();
    }

}
