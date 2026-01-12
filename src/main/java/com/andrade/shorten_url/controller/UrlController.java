package com.andrade.shorten_url.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.andrade.shorten_url.dto.UrlRequest;
import com.andrade.shorten_url.dto.UrlResponse;

import com.andrade.shorten_url.exception.NonexistentUrlException;
import com.andrade.shorten_url.service.UrlService;

import jakarta.servlet.http.HttpServletResponse;

@RequestMapping()
@Controller

public class UrlController {

    @Autowired
    private UrlService service;

    @Value("${http.body}")
    private String http;

    @GetMapping("/")
    public String home() {
        return "shorten";
    }

    @GetMapping("/urls")
    public ResponseEntity<List<UrlResponse>> getAllUrlController() {
        return ResponseEntity.status(200).body(service.getAllUrlService());

    }

    @GetMapping("/{shortUrl}")
    public String redirectToLongUrl(@PathVariable String shortUrl, HttpServletResponse response) {

        try {
            String longUrl = service.getByUrlService(shortUrl).getLongUrl();
            return "redirect:" + longUrl;

        } catch (NonexistentUrlException e) {
            return "error/invalidUrl";
        }
    }

    @PostMapping("api/v1/url")
    public ResponseEntity<?> longToShortController(@Validated @RequestBody UrlRequest url) {
        UrlResponse savedurl = service.longToShortService(url);
        savedurl.setShortUrl(http + savedurl.getShortUrl());
        return ResponseEntity.status(201).body(savedurl);

    }

}
