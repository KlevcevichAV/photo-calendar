package com.klevtcevichav.photocalendar.controller;

import com.klevtcevichav.photocalendar.account.dto.request.AccountUpdateRequestDTO;
import com.klevtcevichav.photocalendar.account.dto.response.AccountResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AccountController {

    ResponseEntity<AccountResponseDTO> createAccount(Long userId);
    ResponseEntity<AccountResponseDTO> updateAccount(AccountUpdateRequestDTO accountUpdateRequestDTO);
    ResponseEntity<SimpleResponseDTO> deleteAccount(Long id);
    ResponseEntity<AccountResponseDTO> getAccount(Long id);
    ResponseEntity<AccountResponseDTO> getAccountByUserId(Long userId);
}
