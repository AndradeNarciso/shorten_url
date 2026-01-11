package com.andrade.shorten_url.util;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;

@Component
public class UrlValidator {

    public Boolean isValidUrl(String url) {

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setRequestMethod("HEAD");
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            conn.setInstanceFollowRedirects(true);

            int status = conn.getResponseCode();

            return status >= 200 && status < 400;

        } catch (Exception e) {
            return false;
        }

        
    }

}
