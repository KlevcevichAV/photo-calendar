package com.klevtcevichav.photocalendar.controller;

import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import com.klevtcevichav.photocalendar.dto.request.AccountUpdateRequestDTO;
import com.klevtcevichav.photocalendar.dto.response.AccountResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AccountController {

    ResponseEntity<AccountResponseDTO> createAccount(Long userId);
    ResponseEntity<AccountResponseDTO> updateAccount(AccountUpdateRequestDTO accountUpdateRequestDTO);
    ResponseEntity<SimpleResponseDTO> deleteAccount(Long id);
    ResponseEntity<AccountResponseDTO> getAccount(Long id);
    ResponseEntity<AccountResponseDTO> getAccountByUserId(Long userId);
}
