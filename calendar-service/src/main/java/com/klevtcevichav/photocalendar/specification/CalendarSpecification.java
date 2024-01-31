package com.klevtcevichav.photocalendar.specification;

import com.klevtcevichav.photocalendar.core.entity.FullAbstractEntity;
import com.klevtcevichav.photocalendar.entity.Photo;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
public class CalendarSpecification {

    public static Specification<Photo> findAllPhotoByAccountIdPeriod(Long accountId, LocalDate from, LocalDate to) {
        return Specification.where(findAllDateOfDeleteIsNull())
                .and(findAllByAccountId(accountId))
                .and(findAllAfterThanDateOfCreationPhoto(from))
                .and(findAllLessThanDateOfCreationPhoto(to));
    }

    public static Specification<Photo> findAllPhotoByAccountIdAndDateOfCreationPhoto(Long accountId, LocalDate dateOfCreationPhoto) {
        return Specification.where(findAllDateOfDeleteIsNull())
                .and(findAllByAccountId(accountId))
                .and(findAllByDateOfCreationPhoto(dateOfCreationPhoto));
    }

    private static Specification<Photo> findAllDateOfDeleteIsNull() {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder
                        .isNull(root.get(FullAbstractEntity.Fields.dateOfDelete)));
    }

    private static Specification<Photo> findAllByAccountId(Long accountId) {
        return Objects.isNull(accountId) ? null : ((root, query, criteriaBuilder) ->
                criteriaBuilder
                        .equal(root.get(Photo.Fields.accountId), accountId));
    }

    private static Specification<Photo> findAllAfterThanDateOfCreationPhoto(LocalDate from) {
        return Objects.isNull(from) ? null : ((root, query, criteriaBuilder) ->
                criteriaBuilder
                        .greaterThanOrEqualTo(root.get(Photo.Fields.dateOfCreationPhoto), from));
    }

    private static Specification<Photo> findAllLessThanDateOfCreationPhoto(LocalDate to) {
        return Objects.isNull(to) ? null : ((root, query, criteriaBuilder) ->
                criteriaBuilder
                        .lessThanOrEqualTo(root.get(Photo.Fields.dateOfCreationPhoto), to));
    }

    private static Specification<Photo> findAllByDateOfCreationPhoto(LocalDate dateOfCreationPhoto) {
        return Objects.isNull(dateOfCreationPhoto) ? null : ((root, query, criteriaBuilder) ->
                criteriaBuilder
                        .equal(root.get(Photo.Fields.dateOfCreationPhoto), dateOfCreationPhoto));
    }
}
