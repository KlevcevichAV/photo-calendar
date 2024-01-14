package com.klevtcevichav.photocalendar.controller;

import com.klevtcevichav.photocalendar.dto.request.CalendarRequestDTO;
import com.klevtcevichav.photocalendar.dto.request.DayRequestDTO;
import com.klevtcevichav.photocalendar.dto.request.MonthRequestDTO;
import com.klevtcevichav.photocalendar.dto.response.CalendarResponseDTO;
import com.klevtcevichav.photocalendar.dto.response.DayResponseDTO;
import org.springframework.http.ResponseEntity;

public interface CalendarController {

    ResponseEntity<CalendarResponseDTO> getCalendar(CalendarRequestDTO calendarRequestDTO);
    ResponseEntity<CalendarResponseDTO> getMonth(MonthRequestDTO monthRequestDTO);
    ResponseEntity<DayResponseDTO> getDay(DayRequestDTO monthRequestDTO);
}
