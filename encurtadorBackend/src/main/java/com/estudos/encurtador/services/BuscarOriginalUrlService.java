package com.estudos.encurtador.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudos.encurtador.model.UrlEntity;
import com.estudos.encurtador.repository.UrlRepository;

@Service
public class BuscarOriginalUrlService {

  @Autowired
  private UrlRepository urlRepository;

  public Optional <UrlEntity> execute(String shortUrl){
    Optional <UrlEntity> urlOriginal = urlRepository.findByShortUrl(shortUrl);
    
    if(urlOriginal.isPresent()){
      UrlEntity urlEntity = urlOriginal.get();
      if(urlEntity.getExpiresDate().isAfter(LocalDateTime.now())){
        return Optional.of(urlEntity);

      }else{
        urlRepository.delete(urlEntity);
      }
    }

    return Optional.empty();

  }
}
