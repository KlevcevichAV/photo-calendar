package com.klevtcevichav.photocalendar.calendar.dto.response;

import com.klevtcevichav.photocalendar.core.dto.AbstractDTO;
import lombok.*;

import java.time.Month;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarResponseDTO extends AbstractDTO {

    private Long year;
    private Month month;
    private List<DayResponseDTO> days;

}
