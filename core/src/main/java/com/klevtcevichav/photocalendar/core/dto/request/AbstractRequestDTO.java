package com.klevtcevichav.photocalendar.core.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractRequestDTO extends SimpleRequestDTO{

    @NotNull
    private Long id;
}
