package com.andrade.shorten_url.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrade.shorten_url.domain.Url;

public interface UrlRepository  extends JpaRepository<Url,Long>{
    
}
