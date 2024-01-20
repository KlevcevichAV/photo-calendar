package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.account.dto.request.AccountUpdateRequestDTO;
import com.klevtcevichav.photocalendar.account.dto.response.AccountResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;

public interface AccountService {

    AccountResponseDTO createAccount(Long userId);
    AccountResponseDTO updateAccount(AccountUpdateRequestDTO accountUpdateRequestDTO);
    SimpleResponseDTO deleteAccount(Long id);
    AccountResponseDTO getAccount(Long id);
    AccountResponseDTO getAccountByUserId(Long userId);
}
