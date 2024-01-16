package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import com.klevtcevichav.photocalendar.dto.mapper.UserRequestMapper;
import com.klevtcevichav.photocalendar.dto.mapper.UserResponseMapper;
import com.klevtcevichav.photocalendar.dto.mapper.UserUpdateRequestMapper;
import com.klevtcevichav.photocalendar.dto.request.UserRequestDTO;
import com.klevtcevichav.photocalendar.dto.request.UserUpdatePasswordDTO;
import com.klevtcevichav.photocalendar.dto.request.UserUpdateRequestDTO;
import com.klevtcevichav.photocalendar.dto.response.UserResponseDTO;
import com.klevtcevichav.photocalendar.entity.User;
import com.klevtcevichav.photocalendar.exception.UserException;
import com.klevtcevichav.photocalendar.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public SimpleResponseDTO registrationUser(UserRequestDTO userRequestDTO) {
        User user = userRequestMapper.userRequestDTOToUser(userRequestDTO);

        if(userRepository.existsByEmailOrUsername(user.getEmail(), user.getUsername())) {
            throw new UserException("This email or username is exist!");
        }

        //TODO: add security for password
        userRepository.save(user);

        return new SimpleResponseDTO();
    }

    @Override
    public UserResponseDTO updateUser(UserUpdateRequestDTO userUpdateRequestDTO) {
        log.info("Start updating user with id: {}", userUpdateRequestDTO.getId());
        User user = userUpdateRequestMapper.userUpdateRequestDTOToUser(userUpdateRequestDTO);
        //TODO change exception
        User oldUser = userRepository.findById(user.getId()).orElseThrow(() -> new UserException("Not found"));
        user.setPassword(oldUser.getPassword());

        userRepository.save(user);

        return userResponseMapper.userToUserResponseDTO(user);
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
        return null;
    }
}
