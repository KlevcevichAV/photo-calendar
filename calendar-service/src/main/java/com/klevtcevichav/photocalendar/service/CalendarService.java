package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.calendar.dto.response.CalendarResponseDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.DayResponseDTO;

import java.time.LocalDate;

public interface CalendarService {

    CalendarResponseDTO getCalendar(Long accountId, LocalDate from, LocalDate to);
    DayResponseDTO getDay(Long accountId, LocalDate day);
}
