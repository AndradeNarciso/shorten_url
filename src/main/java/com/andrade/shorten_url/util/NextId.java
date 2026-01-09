package com.andrade.shorten_url.util;


import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Component
public class NextId {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public long getNextId() {
    
        Long nextId = jdbcTemplate.queryForObject(
            "SELECT last_value + 1 AS next_id FROM url_shortener_id_seq",
            Long.class
        );
        return nextId != null ? nextId : 1L;
    }
    
}
