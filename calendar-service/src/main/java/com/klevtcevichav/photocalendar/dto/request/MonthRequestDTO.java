package com.klevtcevichav.photocalendar.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MonthRequestDTO {

    //    maybe remove because we will have account id in the token :) but now will TAK :0
    private Long accountId;
    private Long year;
    private Long month;
}
