package com.estudos.encurtador.services;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudos.encurtador.model.UrlEntity;
import com.estudos.encurtador.repository.UrlRepository;

@Service
public class GerarShortenUrlService {

  @Autowired
  private UrlRepository urlRepository;

  public String execute (String originalUrl){

    String shortUrl = gerarUrlRandom();
    UrlEntity urlEntity = new UrlEntity();

    urlEntity.setOriginalUrl(originalUrl);
    urlEntity.setShortUrl(shortUrl);
    urlEntity.setExpiresDate(LocalDateTime.now().plusMinutes(5));

    urlRepository.save(urlEntity);
    return shortUrl;
  }


  private String gerarUrlRandom(){
    Random random = new Random();

    int tamanho = random.nextInt(5,11);
    StringBuilder url = new StringBuilder();

    for (int i = 0; i < tamanho; i++) {
      int indexCharacter = random.nextInt(65, 91);
      char character = (char) indexCharacter;
      url.append(character);
    }

    while (tamanho < 10){
      int numRandom = random.nextInt(0,10);
      url.append(numRandom);
      tamanho++;
    }
    
    return url.toString();
  }
}

