package com.andrade.shorten_url.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrade.shorten_url.domain.Url;
import com.andrade.shorten_url.dto.UrlRequest;
import com.andrade.shorten_url.repository.UrlRepository;
import com.andrade.shorten_url.util.NextId;

import io.seruco.encoding.base62.Base62;
import jakarta.transaction.Transactional;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private Base62 encode;

    @Autowired
    private NextId buildShortUrl;

    @Transactional(rollbackOn = Exception.class)
    public void longToShortService(UrlRequest url) {

        String shortUrl = String.valueOf(encode.encode(String.valueOf(buildShortUrl).getBytes()));
        Url newUrl = Url.builder().longUrl(url.url()).shortUrl(shortUrl).build();
        urlRepository.save(newUrl);
    }


    public List<Url> getAllUrlService(){
        return urlRepository.findAll();
    }
}
