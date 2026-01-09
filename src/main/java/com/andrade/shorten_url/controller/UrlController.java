package com.andrade.shorten_url.controller;

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
import com.andrade.shorten_url.service.UrlService;

@RequestMapping("api/v1/url")
@RestController
public class UrlController {
    
    @Autowired
    private UrlService service;

    @PostMapping("/")
    public ResponseEntity<HttpStatus> longToShort(@Validated @RequestBody UrlRequest url){
        service.longToShortService(url);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{short_url}")
    public void getLongUrl(@PathVariable String shortUrl){}
}
