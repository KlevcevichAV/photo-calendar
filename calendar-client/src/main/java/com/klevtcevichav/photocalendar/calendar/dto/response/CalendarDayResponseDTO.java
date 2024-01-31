package com.klevtcevichav.photocalendar.calendar.dto.response;

import com.klevtcevichav.photocalendar.core.dto.response.SimpleResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDayResponseDTO extends SimpleResponseDTO {

    private LocalDate day;
    private Long numberOfPhotosPerDay;
    private Long idMainPhotoOfDay;
    private byte[] mainPhotoOfDay;

}
