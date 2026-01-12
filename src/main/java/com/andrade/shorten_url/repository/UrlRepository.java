package com.andrade.shorten_url.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.andrade.shorten_url.domain.Url;

import jakarta.transaction.Transactional;

public interface UrlRepository  extends JpaRepository<Url,Long>{
    Optional<Url> findByShortUrl(String shortUrl);

    @Transactional
    @Modifying
    @Query("DELETE FROM Url u WHERE u.expirationTime <= :now")
    void deleteUrl(@Param("now") LocalDateTime now);
}
