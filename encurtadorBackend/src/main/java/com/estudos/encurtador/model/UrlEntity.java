package com.estudos.encurtador.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UrlEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String originalUrl;

  private String shortUrl;

  private LocalDateTime expiresDate;

  public Long getId() {
    return id;
  }
  public String getOriginalUrl() {
    return originalUrl;
  }
  public String getShortUrl() {
    return shortUrl;
  }
  public LocalDateTime getExpiresDate() {
    return expiresDate;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setOriginalUrl(String originalUrl) {
    this.originalUrl = originalUrl;
  }
  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }
  public void setExpiresDate(LocalDateTime expiresDate) {
    this.expiresDate = expiresDate;
  }
}
