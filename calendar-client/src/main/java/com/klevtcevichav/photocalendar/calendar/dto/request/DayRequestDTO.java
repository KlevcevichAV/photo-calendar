package com.klevtcevichav.photocalendar.calendar.dto.request;

import com.klevtcevichav.photocalendar.core.dto.request.SimpleRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DayRequestDTO extends SimpleRequestDTO {

    private Long accountId;
    private LocalDate day;
}
