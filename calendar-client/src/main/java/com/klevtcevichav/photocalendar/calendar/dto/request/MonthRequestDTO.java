package com.klevtcevichav.photocalendar.calendar.dto.request;

import com.klevtcevichav.photocalendar.core.dto.request.SimpleRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MonthRequestDTO extends SimpleRequestDTO {

    //    maybe remove because we will have account id in the token :) but now will TAK :0
    private Long accountId;
    private Long year;
    private Long month;
}
