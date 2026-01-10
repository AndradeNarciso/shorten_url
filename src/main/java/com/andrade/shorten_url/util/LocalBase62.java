package com.andrade.shorten_url.util;

import org.springframework.stereotype.Component;

@Component
public class LocalBase62 {
    
private static final String BASE62_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

public  String toBase62(long num) {
    if (num == 0) return "0";
    StringBuilder sb = new StringBuilder();
    while (num > 0) {
        sb.append(BASE62_CHARS.charAt((int)(num % 62)));
        num /= 62;
    }
    return sb.reverse().toString();
}



}
