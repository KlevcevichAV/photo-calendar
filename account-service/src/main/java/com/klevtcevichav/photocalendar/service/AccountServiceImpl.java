package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import com.klevtcevichav.photocalendar.dto.mapper.AccountResponseMapper;
import com.klevtcevichav.photocalendar.dto.mapper.AccountUpdateRequestMapper;
import com.klevtcevichav.photocalendar.dto.request.AccountUpdateRequestDTO;
import com.klevtcevichav.photocalendar.dto.response.AccountResponseDTO;
import com.klevtcevichav.photocalendar.enitty.Account;
import com.klevtcevichav.photocalendar.exception.AccountServiceException;
import com.klevtcevichav.photocalendar.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountResponseMapper accountResponseMapper;
    private final AccountUpdateRequestMapper accountUpdateRequestMapper;

    @Override
    public AccountResponseDTO createAccount(Long userId) {
        if (accountRepository.existsAccountByUserId(userId)) {
            throw new AccountServiceException(String.format("Account with user id=%d exist!", userId));
        }
        Account account = Account.builder().userId(userId).build();

        accountRepository.save(account);
        return accountResponseMapper.accountToAccountResponseDTO(account);
    }

    @Override
    public AccountResponseDTO updateAccount(AccountUpdateRequestDTO accountUpdateRequestDTO) {
        Account oldAccount = accountRepository.findById(accountUpdateRequestDTO.getId())
                .orElseThrow(() -> new AccountServiceException(String.format("Not found account with id: %d", accountUpdateRequestDTO.getId())));
        //TODO find how to do this :)

        Account newAccount = accountUpdateRequestMapper.accountUpdateRequestMapperToAccount(accountUpdateRequestDTO);

        accountRepository.save(newAccount);

        return accountResponseMapper.accountToAccountResponseDTO(newAccount);
    }

    @Override
    public SimpleResponseDTO deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountServiceException(String.format("Not found account with id: %d", id)));

        account.setDateOfDelete(LocalDateTime.now());

        accountRepository.save(account);

        return new SimpleResponseDTO();
    }

    @Override
    public AccountResponseDTO getAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountServiceException(String.format("Not found account with id: %d", id)));

        return accountResponseMapper.accountToAccountResponseDTO(account);
    }

    @Override
    public AccountResponseDTO getAccountByUserId(Long userId) {
        Account account = accountRepository.findAccountByUserId(userId).orElseThrow(() -> new AccountServiceException(String.format("Not found account with id: %d", userId)));

        return accountResponseMapper.accountToAccountResponseDTO(account);
    }
}
