package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.dto.request.CalendarRequestDTO;
import com.klevtcevichav.photocalendar.dto.request.DayRequestDTO;
import com.klevtcevichav.photocalendar.dto.request.MonthRequestDTO;
import com.klevtcevichav.photocalendar.dto.response.CalendarResponseDTO;
import com.klevtcevichav.photocalendar.dto.response.DayResponseDTO;
import com.klevtcevichav.photocalendar.dto.response.PhotoResponseDTO;
import com.klevtcevichav.photocalendar.entity.Photo;
import com.klevtcevichav.photocalendar.repository.PhotoRepository;
import com.klevtcevichav.photocalendar.s3.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CalendarServiceImpl implements CalendarService{

    private final S3Service s3Service;
    private final PhotoRepository photoRepository;

    @Override
    public CalendarResponseDTO getCalendar(CalendarRequestDTO calendarRequestDTO) {
//        check existAccountById(id).orElseThrow(() -> new AccountException("Not found account with id: %s".format(req.getId()))
        Long accountId = calendarRequestDTO.getAccountId();

        LocalDate startDate = LocalDate.of(Math.toIntExact(calendarRequestDTO.getYear()), Month.JANUARY, 1);
        LocalDate finishDate = LocalDate.of(Math.toIntExact(calendarRequestDTO.getYear()), Month.DECEMBER, 31);
        List<Photo> photos = photoRepository.findAllByAccountIdAndDateOfCreationGreaterThanEqualAndDateOfCreationLessThan(
                accountId,
                startDate,
                finishDate);

        return CalendarResponseDTO
                .builder()
                .year(calendarRequestDTO.getYear())
                .days(buildDaysResponseList(startDate, finishDate, photos))
                .build();
    }

    @Override
    public CalendarResponseDTO getMonth(MonthRequestDTO monthRequestDTO) {
//        check existAccountById(id).orElseThrow(() -> new AccountException("Not found account with id: %s".format(req.getId()))
        Long accountId = monthRequestDTO.getAccountId();


        int year = Math.toIntExact(monthRequestDTO.getYear());
        Month month = Month.of(Math.toIntExact(monthRequestDTO.getMonth()));
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate finishDate = LocalDate.of(year, month, month.length(Year.isLeap(year)));

        List<Photo> photos = photoRepository.findAllByAccountIdAndDateOfCreationGreaterThanEqualAndDateOfCreationLessThan(
                accountId,
                startDate,
                finishDate);

        return CalendarResponseDTO
                .builder()
                .year(monthRequestDTO.getYear())
                .month(month)
                .days(buildDaysResponseList(startDate, finishDate, photos))
                .build();
    }

    @Override
    public DayResponseDTO getDay(DayRequestDTO dayRequestDTO) {
//        check existAccountById(id).orElseThrow(() -> new AccountException("Not found account with id: %s".format(req.getId()))
        Long accountId = dayRequestDTO.getAccountId();

        List<Photo> photos = photoRepository.findAllByAccountIdAndDateOfCreationGreaterThanEqualAndDateOfCreationLessThan(
                accountId,
                dayRequestDTO.getDay(),
                dayRequestDTO.getDay());

        return DayResponseDTO
                .builder()
                .date(dayRequestDTO.getDay())
                .photos(buildPhotoResponseList(photos, true))
                .build();
    }

    private List<DayResponseDTO> buildDaysResponseList(LocalDate startDate, LocalDate finishDate, List<Photo> photos) {

        List<DayResponseDTO> response = new ArrayList<>();

        for (LocalDate dayIterator = startDate; dayIterator.isBefore(finishDate); dayIterator = dayIterator.plusDays(1)) {

            LocalDate finalDayIterator = dayIterator;
            List<Photo> photoListForDayIterator = photos.stream().filter(photo -> photo.getDateOfCreation().equals(finalDayIterator)).collect(Collectors.toList());
            response.add(DayResponseDTO
                    .builder()
                    .date(finalDayIterator)
                    .photos(buildPhotoResponseList(photoListForDayIterator, false))
                    .build());
        }
        return response;
    }

    private List<PhotoResponseDTO> buildPhotoResponseList(List<Photo> photos, boolean isGetDay) {
        List<PhotoResponseDTO> photoResponseDTOS = new ArrayList<>();
        for (Photo photo : photos) {
            photoResponseDTOS.add(PhotoResponseDTO
                    .builder()
                    .location(photo.getLocation())
                    .dateOfCreation(photo.getDateOfCreation())
                    .accountId(photo.getAccountId())
                    .fileName(photo.getFileName())
                    .photo((photoResponseDTOS.size() == 0 || isGetDay) ? s3Service.getObject(photo.getKey().toString()) : null)
                    .build());
        }
        return photoResponseDTOS;
    }

}
