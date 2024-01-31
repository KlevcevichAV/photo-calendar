package com.klevtcevichav.photocalendar.specification;

import com.klevtcevichav.photocalendar.core.entity.AbstractEntity;
import com.klevtcevichav.photocalendar.core.entity.FullAbstractEntity;
import com.klevtcevichav.photocalendar.enitty.Account;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

@NoArgsConstructor
public class AccountSpecification {

    public static Specification<Account> findAccountById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }

        return Specification.where(findNotDeletedAccount())
                .and(findById(id));
    }

    public static Specification<Account> findAccountByUserId(Long userId) {
        if (Objects.isNull(userId)) {
            return null;
        }

        return Specification.where(findNotDeletedAccount())
                .and(findByUserId(userId));
    }

    private static Specification<Account> findNotDeletedAccount() {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.isNotNull(root.get(FullAbstractEntity.Fields.dateOfDelete)));
    }

    private static Specification<Account> findById(Long id) {
        return Objects.isNull(id) ? null : ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(AbstractEntity.Fields.id), id));
    }

    private static Specification<Account> findByUserId(Long userId) {
        return Objects.isNull(userId) ? null : ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Account.Fields.userId), userId));
    }
}
