package com.estudos.encurtador.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.estudos.encurtador.dto.UrlRequest;
import com.estudos.encurtador.dto.UrlResponse;
import com.estudos.encurtador.model.UrlEntity;
import com.estudos.encurtador.services.BuscarOriginalUrlService;
import com.estudos.encurtador.services.GerarShortenUrlService;

import jakarta.servlet.http.HttpServletRequest;



@Controller
public class UrlController {

  @Autowired
  private GerarShortenUrlService gerarShortenUrlService;

  @Autowired
  private BuscarOriginalUrlService buscarOriginalUrlService;
  
  @PostMapping("/shorten")
  public ResponseEntity<Object> create(@RequestBody UrlRequest urlRequest, HttpServletRequest servletRequest) {

    String originalUrl = urlRequest.url();
    String shortUrl = gerarShortenUrlService.execute(originalUrl);

    String domain = servletRequest.getRequestURL().toString();
    var urlEncurtada = domain.replace("shorten", shortUrl);

    UrlResponse response = new UrlResponse(urlEncurtada);
    return ResponseEntity.ok(response);
  }

  @GetMapping("{shortUrl}")
  public ResponseEntity<Object> redirect(@PathVariable String shortUrl) {

    Optional <UrlEntity> urlOptional = buscarOriginalUrlService.execute(shortUrl);

    if(urlOptional.isEmpty()){
      return ResponseEntity.notFound().build();
    }

    HttpHeaders headers = new org.springframework.http.HttpHeaders();
    headers.setLocation(URI.create(urlOptional.get().getOriginalUrl()));
    
    
    return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
  }
  
  
}
