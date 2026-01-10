package com.andrade.shorten_url.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.andrade.shorten_url.domain.Url;
import com.andrade.shorten_url.dto.UrlRequest;
import com.andrade.shorten_url.repository.UrlRepository;
import com.andrade.shorten_url.util.LocalBase62;

import jakarta.transaction.Transactional;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Value("${http.bady}")
    private String http;

    @Autowired
    private LocalBase62 base62;

    @Transactional(rollbackOn = Exception.class)
    public void longToShortService(UrlRequest url) {

        Url newUrl = Url.builder().longUrl(url.url()).build();
        Url savedUrl = urlRepository.save(newUrl);
        String shortUrl = base62.toBase62(savedUrl.getId());

        savedUrl.setShortUrl(shortUrl);
        urlRepository.save(savedUrl);

    }

    public List<Url> getAllUrlService() {
        return urlRepository.findAll();
    }
}
