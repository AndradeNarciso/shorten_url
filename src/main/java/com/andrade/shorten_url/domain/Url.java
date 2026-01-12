package com.andrade.shorten_url.domain;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "url")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "long")
    private String longUrl;

    @Column(name = "short")
    private String shortUrl;

    @Column(name = "delete_at")
    private LocalDateTime expirationTime;

    @PrePersist
    private void setDatatime(){
        this.expirationTime=LocalDateTime.now();
        this.expirationTime=this.expirationTime.plusDays(1);
    }
    
}
