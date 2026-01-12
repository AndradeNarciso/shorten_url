package com.andrade.shorten_url.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UrlResponse {

    private String longUrl;
    private String shortUrl;
    
}
