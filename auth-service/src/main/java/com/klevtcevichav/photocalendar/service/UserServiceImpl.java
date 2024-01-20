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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

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

        log.info("Start registration user: {}", userRequestDTO);
        User user = userRequestMapper.userRequestDTOToUser(userRequestDTO);

        if(userRepository.existsByEmailOrUsername(user.getEmail(), user.getUsername())) {
            throw new UserException("This email or username is exist!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        log.info("Start creating account for user with id={}", savedUser.getAccountId());
        ResponseEntity<AccountResponseDTO> response = accountClientApi.createAccount(savedUser.getId());
        if (!HttpStatus.CREATED.equals(response.getStatusCode())
                || Objects.isNull(response.getBody())
                || Objects.isNull(response.getBody().getId())) {

            throw new UserException("Account was not created!");
        }

        savedUser.setAccountId(response.getBody().getId());
        userRepository.save(savedUser);

        log.info("User created: {}", savedUser);
        return new SimpleResponseDTO();
    }

    @Override
    public UserResponseDTO updateUser(UserUpdateRequestDTO userUpdateRequestDTO) {

        log.info("Start updating user with id: {}", userUpdateRequestDTO.getId());
        User user = userRepository.findById(userUpdateRequestDTO.getId()).orElseThrow(() -> new UserException("Not found"));

        userUpdateRequestMapper.updateUserFromDTO(userUpdateRequestDTO, user);

        User savedUser = userRepository.save(user);

        log.info("User with id={} updated: {}", savedUser.getId(), savedUser);
        return userResponseMapper.userToUserResponseDTO(savedUser);
    }

    @Override
    public SimpleResponseDTO delete(Long id) {

        log.info("Start deleting user with id:{}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("Not found"));

        user.setDateOfDelete(LocalDateTime.now());

        userRepository.save(user);

        log.info("User with id: {} deleted", id);
        return new SimpleResponseDTO();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        log.info("Start finding user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found with id : " + id)
        );

        log.info("User with id:{} found:{}", id, user);
        return userResponseMapper.userToUserResponseDTO(user);
    }

    @Override
    public SimpleResponseDTO updatePassword(UserUpdatePasswordDTO userUpdatePasswordDTO) {

        log.info("Start updating password for user with id: {}", userUpdatePasswordDTO.getId());
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
        userRepository.save(user);

        log.info("Password updated for user with id: {}", user.getId());
        return new SimpleResponseDTO();
    }

}
