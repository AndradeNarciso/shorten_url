package com.andrade.shorten_url.mapper;

import org.springframework.stereotype.Component;

import com.andrade.shorten_url.domain.Url;
import com.andrade.shorten_url.dto.UrlResponse;

@Component
public class UrlMapper {

    public UrlResponse ToResponse(Url url) {
        return UrlResponse.
        builder().
        longUrl(url.getLongUrl()).
        shortUrl(url.getShortUrl()).
        build();
    }

}
