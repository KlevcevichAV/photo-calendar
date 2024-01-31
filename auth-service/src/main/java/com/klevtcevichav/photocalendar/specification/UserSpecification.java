package com.klevtcevichav.photocalendar.specification;

import com.klevtcevichav.photocalendar.core.entity.AbstractEntity;
import com.klevtcevichav.photocalendar.core.entity.FullAbstractEntity;
import com.klevtcevichav.photocalendar.entity.AbstractUser;
import com.klevtcevichav.photocalendar.entity.UserProfile;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

@NoArgsConstructor
public class UserSpecification {
//    TODO make one type name for specification f.e. userByEmail/findUserByEmail/getUserByEmail/ByEmail
    public static Specification<UserProfile> findUserProfileById(Long id) {

        return Objects.isNull(id) ? null : Specification.where(findNotDeletedAccount())
                .and(findById(id));
    }

    public static Specification<UserProfile> findByUsernameOrEmail(String username, String email) {
        return Specification.where(findNotDeletedAccount())
                .and(Specification.where(findByUsername(username)).or(findByEmail(email)));
    }

    private static Specification<UserProfile> findNotDeletedAccount() {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.isNotNull(root.get(FullAbstractEntity.Fields.dateOfDelete)));
    }

    private static Specification<UserProfile> findById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }

        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(AbstractEntity.Fields.id), id));

    }

    private static Specification<UserProfile> findByUsername(String username) {
        if (Objects.isNull(username)) {
            return null;
        }

        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(AbstractUser.Fields.username), username));
    }

    private static Specification<UserProfile> findByEmail(String email) {
        if (Objects.isNull(email)) {
            return null;
        }

        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(AbstractUser.Fields.email), email));
    }
}
