package com.klevtcevichav.photocalendar.account.dto.request;

import com.klevtcevichav.photocalendar.core.dto.request.AbstractRequestDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class AccountUpdateRequestDTO extends AbstractRequestDTO {

    @NotNull
    private Long userId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String fullName;
    @NotNull
    //TODO add annotation for check date
    private LocalDate birthDate;
}
