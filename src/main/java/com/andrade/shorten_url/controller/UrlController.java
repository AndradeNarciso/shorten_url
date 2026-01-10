package com.andrade.shorten_url.controller;

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

import com.andrade.shorten_url.domain.Url;
import com.andrade.shorten_url.dto.UrlRequest;
import com.andrade.shorten_url.service.UrlService;

@RequestMapping("api/v1")
@RestController
public class UrlController {

    @Autowired
    private UrlService service;

    @PostMapping("/url")
    public ResponseEntity<HttpStatus> longToShortController(@Validated @RequestBody UrlRequest url) {
        service.longToShortService(url);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{short_url}")
    public void getLongUrl(@PathVariable String shortUrl) {
    }

    @GetMapping("/urls")
    public ResponseEntity<List<Url>> getAllUrlController() {
        return ResponseEntity.status(200).body(service.getAllUrlService());

    }
}
