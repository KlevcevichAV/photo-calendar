package com.klevtcevichav.photocalendar.controller;

import com.klevtcevichav.photocalendar.calendar.client.CalendarClientApi;
import com.klevtcevichav.photocalendar.calendar.dto.response.CalendarResponseDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.DayResponseDTO;
import com.klevtcevichav.photocalendar.service.CalendarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController("/calendars")
@AllArgsConstructor
public class CalendarControllerImpl implements CalendarClientApi {

    private final CalendarService calendarService;

    @Override
    @GetMapping
    public ResponseEntity<CalendarResponseDTO> getCalendar(@RequestParam Long accountId,
                                                           @RequestParam LocalDate from,
                                                           @RequestParam LocalDate to) {

        CalendarResponseDTO calendarResponseDTO = calendarService.getCalendar(accountId, from, to);

        return ResponseEntity.ok(calendarResponseDTO);
    }

    @Override
    @GetMapping("/days/{day}")
    public ResponseEntity<DayResponseDTO> getDay(@PathVariable LocalDate day, @RequestParam Long accountId) {
        DayResponseDTO dayResponseDTO = calendarService.getDay(accountId, day);

        return ResponseEntity.ok(dayResponseDTO);
    }
}
