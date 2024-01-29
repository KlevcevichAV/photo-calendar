package com.klevtcevichav.photocalendar.dto.mapper;

import com.klevtcevichav.photocalendar.account.dto.response.AccountResponseDTO;
import com.klevtcevichav.photocalendar.enitty.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface AccountResponseMapper {

    @Mapping(target = "age", source = "birthDate", qualifiedByName = "calculateAge")
    AccountResponseDTO accountToAccountResponseDTO(Account account);

    @Named("calculateAge")
    static Long calculateAge(LocalDate birthDate) {

        return Objects.isNull(birthDate) ? null : ((long) Period.between(birthDate, LocalDate.now()).getYears());

    }

}
