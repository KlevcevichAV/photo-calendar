package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.dto.request.CalendarRequestDTO;
import com.klevtcevichav.photocalendar.dto.request.DayRequestDTO;
import com.klevtcevichav.photocalendar.dto.request.MonthRequestDTO;
import com.klevtcevichav.photocalendar.dto.response.CalendarResponseDTO;
import com.klevtcevichav.photocalendar.dto.response.DayResponseDTO;

public interface CalendarService {

    CalendarResponseDTO getCalendar(CalendarRequestDTO calendarRequestDTO);
    CalendarResponseDTO getMonth(MonthRequestDTO monthRequestDTO);
    DayResponseDTO getDay(DayRequestDTO dayRequestDTO);
}
