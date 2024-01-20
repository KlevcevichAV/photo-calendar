package com.klevtcevichav.photocalendar.controller;

import com.klevtcevichav.photocalendar.auth.dto.request.UserRequestDTO;
import com.klevtcevichav.photocalendar.auth.dto.request.UserUpdatePasswordDTO;
import com.klevtcevichav.photocalendar.auth.dto.request.UserUpdateRequestDTO;
import com.klevtcevichav.photocalendar.auth.dto.response.UserResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import com.klevtcevichav.photocalendar.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    @PostMapping("/registration")
    public ResponseEntity<SimpleResponseDTO> registrationUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {

        SimpleResponseDTO simpleResponseDTO = userService.registrationUser(userRequestDTO);

        return new ResponseEntity<>(simpleResponseDTO, HttpStatus.CREATED);
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

        SimpleResponseDTO simpleResponseDTO = userService.delete(id);

        return ResponseEntity.ok(simpleResponseDTO);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") @Positive  Long id) {

        UserResponseDTO userResponseDTO = userService.getUserById(id);

        return ResponseEntity.ok(userResponseDTO);
    }

    @Override
    @PatchMapping("/{id}/changePassword")
    public ResponseEntity<SimpleResponseDTO> updatePassword(@RequestBody @ Valid UserUpdatePasswordDTO userUpdatePasswordDTO) {

        SimpleResponseDTO simpleResponseDTO = userService.updatePassword(userUpdatePasswordDTO);

        return ResponseEntity.ok(simpleResponseDTO);
    }

}
