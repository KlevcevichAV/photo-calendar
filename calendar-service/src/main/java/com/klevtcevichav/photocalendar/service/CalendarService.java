package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.calendar.dto.request.CalendarRequestDTO;
import com.klevtcevichav.photocalendar.calendar.dto.request.DayRequestDTO;
import com.klevtcevichav.photocalendar.calendar.dto.request.MonthRequestDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.CalendarResponseDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.DayResponseDTO;

public interface CalendarService {

    CalendarResponseDTO getCalendar(CalendarRequestDTO calendarRequestDTO);
    CalendarResponseDTO getMonth(MonthRequestDTO monthRequestDTO);
    DayResponseDTO getDay(DayRequestDTO dayRequestDTO);
}
