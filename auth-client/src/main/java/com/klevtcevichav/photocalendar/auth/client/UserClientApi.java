package com.klevtcevichav.photocalendar.auth.client;

import com.klevtcevichav.photocalendar.auth.dto.request.UserRequestDTO;
import com.klevtcevichav.photocalendar.auth.dto.request.UserUpdatePasswordDTO;
import com.klevtcevichav.photocalendar.auth.dto.request.UserUpdateRequestDTO;
import com.klevtcevichav.photocalendar.auth.dto.response.UserResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("user")
public interface UserClientApi {

    @PostMapping("/registration")
    ResponseEntity<SimpleResponseDTO> registrationUser(@RequestBody @Valid UserRequestDTO userRequestDTO);

    @PatchMapping("/{id}")
    ResponseEntity<UserResponseDTO> updateUser(@RequestBody @Valid UserUpdateRequestDTO userUpdateRequestDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<SimpleResponseDTO> delete(@PathVariable("id") @Positive Long id);

    
    @GetMapping("/{id}")
    ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") @Positive  Long id);

    
    @PatchMapping("/{id}/change-password")
    ResponseEntity<SimpleResponseDTO> updatePassword(@RequestBody @ Valid UserUpdatePasswordDTO userUpdatePasswordDTO);
}
