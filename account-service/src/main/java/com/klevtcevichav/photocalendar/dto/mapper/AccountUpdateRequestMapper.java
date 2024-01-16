package com.klevtcevichav.photocalendar.dto.mapper;

import com.klevtcevichav.photocalendar.dto.request.AccountUpdateRequestDTO;
import com.klevtcevichav.photocalendar.enitty.Account;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountUpdateRequestMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAccountFromDTO(AccountUpdateRequestDTO accountUpdateRequestDTO, @MappingTarget Account account);
}
