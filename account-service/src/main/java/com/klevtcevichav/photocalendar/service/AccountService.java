package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.account.dto.request.AccountUpdateRequestDTO;
import com.klevtcevichav.photocalendar.account.dto.response.AccountResponseDTO;

public interface AccountService {

    AccountResponseDTO createAccount(Long userId);
    AccountResponseDTO updateAccount(AccountUpdateRequestDTO accountUpdateRequestDTO);
    void deleteAccount(Long id);
    AccountResponseDTO getAccount(Long id);
    AccountResponseDTO getAccountByUserId(Long userId);
    void deleteAccountByUserId(Long id);
}
