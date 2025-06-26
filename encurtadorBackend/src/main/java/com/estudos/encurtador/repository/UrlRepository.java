package com.estudos.encurtador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudos.encurtador.model.UrlEntity;

public interface UrlRepository extends JpaRepository <UrlEntity,Long> {

  Optional <UrlEntity> findByShortUrl(String shortUrl);
}
