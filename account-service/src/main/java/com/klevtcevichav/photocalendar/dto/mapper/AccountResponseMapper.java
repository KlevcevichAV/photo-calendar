package com.klevtcevichav.photocalendar.dto.mapper;

import com.klevtcevichav.photocalendar.account.dto.response.AccountResponseDTO;
import com.klevtcevichav.photocalendar.enitty.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountResponseMapper {

    AccountResponseDTO accountToAccountResponseDTO(Account account);
}
