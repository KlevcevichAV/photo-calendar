package com.klevtcevichav.photocalendar.controller;

import com.klevtcevichav.photocalendar.account.client.AccountClientApi;
import com.klevtcevichav.photocalendar.account.dto.request.AccountUpdateRequestDTO;
import com.klevtcevichav.photocalendar.account.dto.response.AccountResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import com.klevtcevichav.photocalendar.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/accounts")
public class AccountControllerImpl implements AccountClientApi {

    private final AccountService accountService;

    @Override
    @PostMapping("/{userId}")
    public ResponseEntity<AccountResponseDTO> createAccount(@PathVariable(name = "userId") @Positive Long userId) {
        AccountResponseDTO accountResponseDTO = accountService.createAccount(userId);

        return new ResponseEntity<>(accountResponseDTO, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> updateAccount(@RequestBody @Valid AccountUpdateRequestDTO accountUpdateRequestDTO) {
        AccountResponseDTO accountResponseDTO = accountService.updateAccount(accountUpdateRequestDTO);

        return ResponseEntity.ok(accountResponseDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleResponseDTO> deleteAccount(@PathVariable(name = "id") @Positive Long id) {
        SimpleResponseDTO responseDTO = accountService.deleteAccount(id);

        return ResponseEntity.ok(responseDTO);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getAccount(@PathVariable(name = "id") @Positive Long id) {
        AccountResponseDTO accountResponseDTO = accountService.getAccount(id);

        return ResponseEntity.ok(accountResponseDTO);
    }

    @Override
    @GetMapping("/user/{userId}")
    public ResponseEntity<AccountResponseDTO> getAccountByUserId(@PathVariable(name = "userId") @Positive Long userId) {
        AccountResponseDTO accountResponseDTO = accountService.getAccountByUserId(userId);

        return ResponseEntity.ok(accountResponseDTO);
    }

}
