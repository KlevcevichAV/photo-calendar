package com.klevtcevichav.photocalendar.service;

import com.klevtcevichav.photocalendar.account.client.AccountClientApi;
import com.klevtcevichav.photocalendar.account.dto.response.AccountResponseDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.CalendarDayResponseDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.CalendarResponseDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.DayResponseDTO;
import com.klevtcevichav.photocalendar.calendar.dto.response.PhotoResponseDTO;
import com.klevtcevichav.photocalendar.core.exception.NotFoundException;
import com.klevtcevichav.photocalendar.dto.mapper.PhotoResponseMapper;
import com.klevtcevichav.photocalendar.entity.Photo;
import com.klevtcevichav.photocalendar.exception.CalendarBusinessException;
import com.klevtcevichav.photocalendar.repository.PhotoRepository;
import com.klevtcevichav.photocalendar.s3.S3Service;
import com.klevtcevichav.photocalendar.specification.CalendarSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CalendarServiceImpl implements CalendarService{

    private final S3Service s3Service;
    private final PhotoRepository photoRepository;
    private final AccountClientApi accountClientApi;
    private final PhotoResponseMapper photoResponseMapper;

    @Override
    public CalendarResponseDTO getCalendar(Long accountId, LocalDate from, LocalDate to) {

        log.info("Start finding calendar with account id: {}.From {} to {}", accountId, from, to);
        getAccount(accountId);

        if (from.getYear() != to.getYear()) {
            throw new CalendarBusinessException("This period more than 1 year! Please shorten the period!");
        }
        List<Photo> photos = photoRepository.findAll(CalendarSpecification.findAllPhotoByAccountIdPeriod(accountId, from, to));

        log.info("Calendar found and start building response for calendar: {}", photos);
        return CalendarResponseDTO
                .builder()
                .year((long) from.getYear())
                .month(from.getMonth().equals(to.getMonth()) ? from.getMonth() : null)
                .days(buildDaysResponseList(from, to.plusDays(1), photos))
                .numberOfDays(ChronoUnit.DAYS.between(from, to) + 1)
                .build();
    }

    @Override
    public DayResponseDTO getDay(Long accountId, LocalDate day, PageRequest pageRequest) {

        log.info("Start finding photos for date:{}  with account id: {}",
                accountId, day);
        getAccount(accountId);

        Page<Photo> photos = photoRepository.findAll(CalendarSpecification.findAllPhotoByAccountIdAndDateOfCreationPhoto(accountId, day), pageRequest);

        log.info("Photos for date found and start building response: {}", photos);

        return DayResponseDTO
                .builder()
                .date(day)
                .photos(buildPhotoResponseList(photos.toList()))
                .pageNumber((long) pageRequest.getPageNumber())
                .pageSize((long) pageRequest.getPageSize())
                .totalElements(photos.getTotalElements())
                .totalPages((long) photos.getTotalPages())
                .build();
    }

    private void getAccount(Long accountId) {

        log.info("Start calling Account service for finding account with id: {}", accountId);
        ResponseEntity<AccountResponseDTO> accountResponseEntity = accountClientApi.getAccount(accountId);
        if (!HttpStatus.OK.equals(accountResponseEntity.getStatusCode()) || Objects.isNull(accountResponseEntity.getBody())) {
            throw new NotFoundException("Can not find account by id: " + accountId);
        }
    }

    private List<CalendarDayResponseDTO> buildDaysResponseList(LocalDate startDate, LocalDate finishDate, List<Photo> photos) {

        log.info("Start building list day response for photos: {}", photos);
        List<CalendarDayResponseDTO> response = new ArrayList<>();
        for (LocalDate dayIterator = startDate; dayIterator.isBefore(finishDate.plusDays(1)); dayIterator = dayIterator.plusDays(1)) {

            LocalDate finalDayIterator = dayIterator;
            List<Photo> photoListForDayIterator = photos.stream().filter(photo -> photo.getDateOfCreationPhoto().equals(finalDayIterator)).collect(Collectors.toList());
            response.add(CalendarDayResponseDTO
                    .builder()
                    .day(dayIterator)
                    .numberOfPhotosPerDay((long) photoListForDayIterator.size())
                    .idMainPhotoOfDay(photoListForDayIterator.size() > 0 ? photoListForDayIterator.get(0).getId() : null)
                    .mainPhotoOfDay(photoListForDayIterator.size() > 0 ? s3Service.getObject(photoListForDayIterator.get(0).getKey().toString()): null)
                    .build());
        }
        log.info("List DayResponse built: {}", response);
        return response;
    }

    private List<PhotoResponseDTO> buildPhotoResponseList(List<Photo> photos) {

        List<PhotoResponseDTO> photoResponseDTOS = new ArrayList<>();

        if(photos.isEmpty()) {
            log.info("List photo is empty!");
            return photoResponseDTOS;
        }

        log.info("Start building list photo for day: {}", photos.get(0).getDateOfCreationPhoto());
        for (Photo photo : photos) {
            PhotoResponseDTO photoResponseDTO = photoResponseMapper.photoToPhotoResponseDTO(photo);
            photoResponseDTO.setPhoto(s3Service.getObject(photo.getKey().toString()));
            photoResponseDTOS.add(photoResponseDTO);
        }
        log.info("List list photo for day: {} built: {}", photos.get(0).getDateOfCreationPhoto(), photoResponseDTOS);
        return photoResponseDTOS;
    }

}
