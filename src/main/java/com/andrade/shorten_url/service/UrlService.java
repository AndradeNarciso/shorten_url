package com.andrade.shorten_url.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.andrade.shorten_url.domain.Url;
import com.andrade.shorten_url.dto.UrlRequest;
import com.andrade.shorten_url.dto.UrlResponse;
import com.andrade.shorten_url.exception.InvalidUrlException;
import com.andrade.shorten_url.exception.NonexistentUrlException;
import com.andrade.shorten_url.mapper.UrlMapper;
import com.andrade.shorten_url.repository.UrlRepository;
import com.andrade.shorten_url.util.LocalBase62;
import com.andrade.shorten_url.util.UrlValidator;

import jakarta.transaction.Transactional;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Value("${http.bady}")
    private String http;

    @Autowired
    private LocalBase62 base62;

    @Autowired
    private UrlMapper mapperUrl;

    @Autowired
    private UrlValidator validator;

    @Transactional(rollbackOn = Exception.class)
    public UrlResponse longToShortService(UrlRequest url) {

        if(!validator.isValidUrl(url.url())) {
             throw  new InvalidUrlException("Url was not valid");
        }

        Url newUrl = Url.builder().longUrl(url.url()).build();
        Url savedUrl = urlRepository.save(newUrl);
        String shortUrl = base62.toBase62(savedUrl.getId());

        savedUrl.setShortUrl(shortUrl);

        Url urlWithShort=urlRepository.save(savedUrl);
        urlWithShort.setShortUrl(http+urlWithShort.getShortUrl());
        return mapperUrl.ToResponse(urlWithShort);

    }

    public List<UrlResponse> getAllUrlService() {
        return urlRepository.findAll().stream().map(url -> {
            url.setShortUrl(url.getShortUrl());
            return mapperUrl.ToResponse(url);
        }).toList();
    }

    public Url getByUrlService(String shortUrl) throws NonexistentUrlException {
        return urlRepository.findByShortUrl(shortUrl).map(url -> url)
                .orElseThrow(() -> new NonexistentUrlException("Url not found"));
    }
}
