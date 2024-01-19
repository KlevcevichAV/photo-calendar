package com.klevtcevichav.photocalendar.calendar.dto.request;

import com.klevtcevichav.photocalendar.core.dto.request.SimpleRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalendarRequestDTO extends SimpleRequestDTO {

//    maybe remove because we will have account id in the token :) but now will TAK :0
    private Long accountId;
    private Long year;

}
