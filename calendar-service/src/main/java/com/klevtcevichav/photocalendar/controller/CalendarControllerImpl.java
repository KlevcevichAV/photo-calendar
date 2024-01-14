package com.klevtcevichav.photocalendar.controller;

import com.klevtcevichav.photocalendar.dto.request.CalendarRequestDTO;
import com.klevtcevichav.photocalendar.dto.request.DayRequestDTO;
import com.klevtcevichav.photocalendar.dto.request.MonthRequestDTO;
import com.klevtcevichav.photocalendar.dto.response.CalendarResponseDTO;
import com.klevtcevichav.photocalendar.dto.response.DayResponseDTO;
import com.klevtcevichav.photocalendar.service.CalendarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/calendar")
@AllArgsConstructor
public class CalendarControllerImpl implements CalendarController{

    private final CalendarService calendarService;

    @Override
    public ResponseEntity<CalendarResponseDTO> getCalendar(@RequestBody @Valid CalendarRequestDTO calendarRequestDTO) {

        CalendarResponseDTO calendarResponseDTO = calendarService.getCalendar(calendarRequestDTO);

        return new ResponseEntity<>(calendarResponseDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CalendarResponseDTO> getMonth(@RequestBody @Valid MonthRequestDTO monthRequestDTO) {
        CalendarResponseDTO calendarResponseDTO = calendarService.getMonth(monthRequestDTO);

        return new ResponseEntity<>(calendarResponseDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DayResponseDTO> getDay(@RequestBody @Valid DayRequestDTO monthRequestDTO) {
        return null;
    }
}
