package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.account.client.AccountClientApi;
import com.klevtcevichav.photocalendar.account.dto.response.AccountResponseDTO;
import com.klevtcevichav.photocalendar.auth.dto.request.UserRequestDTO;
import com.klevtcevichav.photocalendar.auth.dto.request.UserUpdatePasswordDTO;
import com.klevtcevichav.photocalendar.auth.dto.request.UserUpdateRequestDTO;
import com.klevtcevichav.photocalendar.auth.dto.response.UserResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import com.klevtcevichav.photocalendar.dto.mapper.UserRequestMapper;
import com.klevtcevichav.photocalendar.dto.mapper.UserResponseMapper;
import com.klevtcevichav.photocalendar.dto.mapper.UserUpdateRequestMapper;
import com.klevtcevichav.photocalendar.entity.User;
import com.klevtcevichav.photocalendar.exception.UserException;
import com.klevtcevichav.photocalendar.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRequestMapper userRequestMapper;
    private final UserUpdateRequestMapper userUpdateRequestMapper;
    private final UserResponseMapper userResponseMapper;
    private final PasswordEncoder passwordEncoder;
    private final AccountClientApi accountClientApi;

    @Override
    public SimpleResponseDTO registrationUser(UserRequestDTO userRequestDTO) {
        User user = userRequestMapper.userRequestDTOToUser(userRequestDTO);

        if(userRepository.existsByEmailOrUsername(user.getEmail(), user.getUsername())) {
            throw new UserException("This email or username is exist!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        ResponseEntity<AccountResponseDTO> response = accountClientApi.createAccount(savedUser.getId());
        // TODO:
        savedUser.setAccountId(response.getBody().getId());

        return new SimpleResponseDTO();
    }

    @Override
    public UserResponseDTO updateUser(UserUpdateRequestDTO userUpdateRequestDTO) {
        User user = userRepository.findById(userUpdateRequestDTO.getId()).orElseThrow(() -> new UserException("Not found"));

        log.info("Start updating user with id: {}", userUpdateRequestDTO.getId());
        userUpdateRequestMapper.updateUserFromDTO(userUpdateRequestDTO, user);

        User savedUsed = userRepository.save(user);

        return userResponseMapper.userToUserResponseDTO(savedUsed);
    }

    @Override
    public SimpleResponseDTO delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("Not found"));

        user.setDateOfDelete(LocalDateTime.now());

        userRepository.save(user);

        return new SimpleResponseDTO();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found with id : " + id)
        );

        return userResponseMapper.userToUserResponseDTO(user);
    }

    @Override
    public SimpleResponseDTO updatePassword(UserUpdatePasswordDTO userUpdatePasswordDTO) {
        User user = userRepository.findById(userUpdatePasswordDTO.getId())
                .orElseThrow(() -> new UserException("User not found with id : %d".formatted(userUpdatePasswordDTO.getId()))
                );

        if (!userUpdatePasswordDTO.getPassword().equals(userUpdatePasswordDTO.getConfirmPassword())) {
            throw new RuntimeException("Password and confirm password are not equals!");
        }

        if (user.getPassword().equals(userUpdatePasswordDTO.getPassword())) {
            throw new RuntimeException("Old password and new password is equals!");
        }

        user.setPassword(passwordEncoder.encode(userUpdatePasswordDTO.getPassword()));

        return new SimpleResponseDTO();
    }

}
