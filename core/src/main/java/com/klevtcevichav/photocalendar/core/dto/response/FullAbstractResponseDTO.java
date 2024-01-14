package com.klevtcevichav.photocalendar.core.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public abstract class FullAbstractResponseDTO extends AbstractResponseDTO{

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH.mm")
    private LocalDateTime dateOfCreation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH.mm")
    private LocalDateTime dateOfLastUpdate;
}
