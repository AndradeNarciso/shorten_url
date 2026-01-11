package com.andrade.shorten_url.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import com.andrade.shorten_url.exception.InvalidUrlException;
import com.andrade.shorten_url.exception.NonexistentUrlException;
import com.andrade.shorten_url.service.UrlService;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@RequestMapping()
@Controller

public class UrlController {

    @Autowired
    private UrlService service;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @PostMapping("api/v1/url")
    public ResponseEntity<?> longToShortController(@Validated @RequestBody UrlRequest url) {
        try {
            return ResponseEntity.status(201).body(service.longToShortService(url));

        } catch (InvalidUrlException e) {
            Context context = new Context();
            context.setVariable("message", e.getMessage());

            String htmlErro = templateEngine.process("error/invalidUrl", context);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/html; charset=UTF-8");
            return new ResponseEntity<>(htmlErro, headers, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<?> redirectToLongUrl(@PathVariable String shortUrl) {
        try {
            String longUrl = service.getByUrlService(shortUrl).getLongUrl();

            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                    .location(URI.create(longUrl))
                    .build();

        } catch (NonexistentUrlException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/urls")
    public ResponseEntity<List<UrlResponse>> getAllUrlController() {
        return ResponseEntity.status(200).body(service.getAllUrlService());

    }

    @GetMapping("/")
    public String home() {
        return "shorten";
    }
}
