package com.andrade.shorten_url.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrade.shorten_url.dto.UrlRequest;
import com.andrade.shorten_url.repository.UrlRepository;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;
    
    public void longToShort(UrlRequest url){
        
    }
}
