package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.account.dto.request.AccountUpdateRequestDTO;
import com.klevtcevichav.photocalendar.account.dto.response.AccountResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import com.klevtcevichav.photocalendar.core.exception.NotFoundException;
import com.klevtcevichav.photocalendar.dto.mapper.AccountResponseMapper;
import com.klevtcevichav.photocalendar.dto.mapper.AccountUpdateRequestMapper;
import com.klevtcevichav.photocalendar.enitty.Account;
import com.klevtcevichav.photocalendar.exception.AccountBusinessException;
import com.klevtcevichav.photocalendar.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountResponseMapper accountResponseMapper;
    private final AccountUpdateRequestMapper accountUpdateRequestMapper;

    @Override
    @Transactional
    public AccountResponseDTO createAccount(Long userId) {
        log.info("Start creating account with user id: {}", userId);

        if (accountRepository.existsAccountByUserId(userId)) {
            throw new AccountBusinessException(String.format("Account with user id=%d exist!", userId));
        }
        Account account = Account.builder().userId(userId).build();

        accountRepository.save(account);
        log.info("Account with user id {} created!", account);
        return accountResponseMapper.accountToAccountResponseDTO(account);
    }

    @Override
    @Transactional
    public AccountResponseDTO updateAccount(AccountUpdateRequestDTO accountUpdateRequestDTO) {
        log.info("Start updating account info: {}", accountUpdateRequestDTO);

        Account account = accountRepository.findById(accountUpdateRequestDTO.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Not found account with id: %d", accountUpdateRequestDTO.getId())));

        accountUpdateRequestMapper.updateAccountFromDTO(accountUpdateRequestDTO, account);

        accountRepository.save(account);

        log.info("Account with id {} updated: {}!", account.getId(), account);
        return accountResponseMapper.accountToAccountResponseDTO(account);
    }

    @Override
    @Transactional
    public void deleteAccount(Long id) {
        log.info("Start deleting account with id: {}", id);

        Account account = accountRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Not found account with id: %d", id)));

        account.setDateOfDelete(LocalDateTime.now());

        accountRepository.save(account);

        log.info("Account with id {} deleted!", id);
        new SimpleResponseDTO();
    }

    @Override
    public AccountResponseDTO getAccount(Long id) {
        log.info("Start finding account with id: {}", id);
        Account account = accountRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Not found account with id: %d", id)));

        log.info("Account with id={} found: {} ", id, account);
        return accountResponseMapper.accountToAccountResponseDTO(account);
    }

    @Override
    public AccountResponseDTO getAccountByUserId(Long userId) {
        log.info("Start finding account with user id: {}", userId);

        Account account = accountRepository.findAccountByUserId(userId).orElseThrow(() -> new NotFoundException(String.format("Not found account with id: %d", userId)));

        log.info("Account with user id={} found: {}", userId, account);
        return accountResponseMapper.accountToAccountResponseDTO(account);
    }

    @Override
    @Transactional
    public void deleteAccountByUserId(Long userId) {
        log.info("Start deleting account with user id: {}", userId);

        Account account = accountRepository.findAccountByUserId(userId).orElseThrow(() -> new NotFoundException(String.format("Not found account with user id: %d", userId)));

        account.setDateOfDelete(LocalDateTime.now());

        accountRepository.save(account);

        log.info("Account with user id {} deleted!", userId);
        new SimpleResponseDTO();
    }
}
