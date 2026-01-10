package com.andrade.shorten_url.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.seruco.encoding.base62.Base62;

@Configuration
public class Base62Configuration {

    @Bean
    public Base62 base62() {
        return Base62.createInstance();
    }
}
