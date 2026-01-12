package com.andrade.shorten_url.util;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.andrade.shorten_url.repository.UrlRepository;

@Service
public class DeleteUrl {

    @Autowired
    private UrlRepository urlRepository;

    @Scheduled(fixedRate = 30*60000)
    public void deleteExpiredUrls() {
        LocalDateTime now = LocalDateTime.now();
        urlRepository.deleteUrl(now);

    }
}
