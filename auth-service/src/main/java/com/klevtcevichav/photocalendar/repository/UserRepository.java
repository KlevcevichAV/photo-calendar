package com.klevtcevichav.photocalendar.repository;

import com.klevtcevichav.photocalendar.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long> {

    boolean existsByEmailOrUsername(String email, String username);
}
