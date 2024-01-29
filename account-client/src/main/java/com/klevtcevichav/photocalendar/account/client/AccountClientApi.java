package com.klevtcevichav.photocalendar.account.client;

import com.klevtcevichav.photocalendar.account.dto.request.AccountUpdateRequestDTO;
import com.klevtcevichav.photocalendar.account.dto.response.AccountResponseDTO;
import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account")
public interface AccountClientApi {

    @PostMapping("/{userId}")
    ResponseEntity<AccountResponseDTO> createAccount(@PathVariable(name = "userId") @Positive Long userId);

    
    @PutMapping("/{id}")
    ResponseEntity<AccountResponseDTO> updateAccount(@RequestBody @Valid AccountUpdateRequestDTO accountUpdateRequestDTO);

    
    @DeleteMapping("/{id}")
    ResponseEntity<SimpleResponseDTO> deleteAccount(@PathVariable(name = "id") @Positive Long id);

    
    @GetMapping("/{id}")
    ResponseEntity<AccountResponseDTO> getAccount(@PathVariable(name = "id") @Positive Long id);

    
    @GetMapping("/user/{userId}")
    ResponseEntity<AccountResponseDTO> getAccountByUserId(@PathVariable(name = "userId") @Positive Long userId);

    @DeleteMapping("/user/{userId}")
    ResponseEntity<SimpleResponseDTO> deleteAccountByUserId(@PathVariable(name = "userId") @Positive Long userId);

}
