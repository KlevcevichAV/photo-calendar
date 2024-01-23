package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.account.client.AccountClientApi;
import com.klevtcevichav.photocalendar.account.dto.response.AccountResponseDTO;
import com.klevtcevichav.photocalendar.auth.dto.request.UserRequestDTO;
import com.klevtcevichav.photocalendar.auth.dto.request.UserUpdatePasswordDTO;
import com.klevtcevichav.photocalendar.auth.dto.request.UserUpdateRequestDTO;
import com.klevtcevichav.photocalendar.auth.dto.response.UserResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import com.klevtcevichav.photocalendar.core.exception.NotFoundException;
import com.klevtcevichav.photocalendar.dto.mapper.UserRequestMapper;
import com.klevtcevichav.photocalendar.dto.mapper.UserResponseMapper;
import com.klevtcevichav.photocalendar.dto.mapper.UserUpdateRequestMapper;
import com.klevtcevichav.photocalendar.entity.UserProfile;
import com.klevtcevichav.photocalendar.exception.UserBusinessException;
import com.klevtcevichav.photocalendar.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public SimpleResponseDTO registrationUser(UserRequestDTO userRequestDTO) {

        log.info("Start registration user: {}", userRequestDTO);
        UserProfile userProfile = userRequestMapper.userRequestDTOToUser(userRequestDTO);

        if(userRepository.existsByEmailOrUsername(userProfile.getEmail(), userProfile.getUsername())) {
            throw new UserBusinessException("This email or username is exist!");
        }

        userProfile.setPassword(passwordEncoder.encode(userProfile.getPassword()));
        UserProfile savedUserProfile = userRepository.save(userProfile);

        log.info("Start creating account for user with id={}", savedUserProfile.getAccountId());
        ResponseEntity<AccountResponseDTO> response = accountClientApi.createAccount(savedUserProfile.getId());
        if (!HttpStatus.CREATED.equals(response.getStatusCode())
                || Objects.isNull(response.getBody())
                || Objects.isNull(response.getBody().getId())) {

            throw new UserBusinessException("Account was not created!");
        }

        savedUserProfile.setAccountId(response.getBody().getId());
        userRepository.save(savedUserProfile);

        log.info("User created: {}", savedUserProfile);
        return new SimpleResponseDTO();
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(UserUpdateRequestDTO userUpdateRequestDTO) {

        log.info("Start updating user with id: {}", userUpdateRequestDTO.getId());
        UserProfile userProfile = userRepository.findById(userUpdateRequestDTO.getId()).orElseThrow(() -> new NotFoundException("Not found"));

        userUpdateRequestMapper.updateUserFromDTO(userUpdateRequestDTO, userProfile);

        UserProfile savedUserProfile = userRepository.save(userProfile);

        log.info("User with id={} updated: {}", savedUserProfile.getId(), savedUserProfile);
        return userResponseMapper.userToUserResponseDTO(savedUserProfile);
    }

    @Override
    @Transactional
    public SimpleResponseDTO delete(Long id) {

        log.info("Start deleting user with id:{}", id);
        UserProfile userProfile = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));

        userProfile.setDateOfDelete(LocalDateTime.now());

        userRepository.save(userProfile);

        log.info("User with id: {} deleted", id);
        return new SimpleResponseDTO();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        log.info("Start finding user with id: {}", id);
        UserProfile userProfile = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id : " + id)
        );

        log.info("User with id:{} found:{}", id, userProfile);
        return userResponseMapper.userToUserResponseDTO(userProfile);
    }

    @Override
    @Transactional
    public SimpleResponseDTO updatePassword(UserUpdatePasswordDTO userUpdatePasswordDTO) {

        log.info("Start updating password for user with id: {}", userUpdatePasswordDTO.getId());
        UserProfile userProfile = userRepository.findById(userUpdatePasswordDTO.getId())
                .orElseThrow(() -> new NotFoundException("User not found with id : %d".formatted(userUpdatePasswordDTO.getId()))
                );

        if (!userUpdatePasswordDTO.getPassword().equals(userUpdatePasswordDTO.getConfirmPassword())) {
            throw new UserBusinessException("Password and confirm password are not equals!");
        }

        if (userProfile.getPassword().equals(userUpdatePasswordDTO.getPassword())) {
            throw new UserBusinessException("Old password and new password is equals!");
        }

        userProfile.setPassword(passwordEncoder.encode(userUpdatePasswordDTO.getPassword()));
        userRepository.save(userProfile);

        log.info("Password updated for user with id: {}", userProfile.getId());
        return new SimpleResponseDTO();
    }

}
