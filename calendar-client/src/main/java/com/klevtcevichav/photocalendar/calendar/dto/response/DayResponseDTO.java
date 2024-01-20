package com.klevtcevichav.photocalendar.calendar.dto.response;

import com.klevtcevichav.photocalendar.core.dto.AbstractDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DayResponseDTO extends AbstractDTO {

    private LocalDate date;
    private List<PhotoResponseDTO> photos;
}
