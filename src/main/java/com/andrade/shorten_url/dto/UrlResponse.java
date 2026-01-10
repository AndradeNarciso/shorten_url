package com.andrade.shorten_url.dto;

import lombok.Builder;

@Builder
public record  UrlResponse(String longUrl, String shortUrl) {
    
}
