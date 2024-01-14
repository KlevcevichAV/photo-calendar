package com.klevtcevichav.photocalendar.dto.mapper;

import com.klevtcevichav.photocalendar.dto.request.AccountUpdateRequestDTO;
import com.klevtcevichav.photocalendar.enitty.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountUpdateRequestMapper {

    Account accountUpdateRequestMapperToAccount(AccountUpdateRequestDTO accountUpdateRequestDTO);
}
