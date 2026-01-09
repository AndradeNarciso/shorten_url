package com.andrade.shorten_url.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrade.shorten_url.dto.UrlRequest;

@RequestMapping("api/v1/url")
@RestController
public class UrlController {
    

    @PostMapping("/")
    public ResponseEntity<HttpStatus> longToShort(@RequestBody UrlRequest url){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
