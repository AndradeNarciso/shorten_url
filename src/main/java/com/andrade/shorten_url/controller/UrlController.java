package com.andrade.shorten_url.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrade.shorten_url.dto.UrlRequest;
import com.andrade.shorten_url.dto.UrlResponse;
import com.andrade.shorten_url.exception.NonexistentUrlException;
import com.andrade.shorten_url.service.UrlService;

@RequestMapping()
@RestController

public class UrlController {

    @Autowired
    private UrlService service;

    @PostMapping("api/v1/url")
    public ResponseEntity<UrlResponse> longToShortController(@Validated @RequestBody UrlRequest url) {
        return ResponseEntity.status(201).body(service.longToShortService(url));
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToLongUrl(@PathVariable String shortUrl) {
        try {
            String longUrl = service.getByUrlService(shortUrl).getLongUrl();

            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                    .location(URI.create(longUrl))
                    .build();

        } catch (NonexistentUrlException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/urls")
    public ResponseEntity<List<UrlResponse>> getAllUrlController() {
        return ResponseEntity.status(200).body(service.getAllUrlService());

    }
}
