package com.klevtcevichav.photocalendar.calendar.dto.response;

import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Month;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarResponseDTO extends SimpleResponseDTO {

    private Long year;
    private Month month;
    private Long numberOfDays;
    private List<CalendarDayResponseDTO> days;

}
