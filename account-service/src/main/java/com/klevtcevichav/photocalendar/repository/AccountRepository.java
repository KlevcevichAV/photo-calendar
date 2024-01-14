package com.klevtcevichav.photocalendar.repository;

import com.klevtcevichav.photocalendar.enitty.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsAccountByUserId(Long userId);
    Optional<Account> findAccountByUserId(Long userId);
}
