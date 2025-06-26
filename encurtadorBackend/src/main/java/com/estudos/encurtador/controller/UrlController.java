package com.estudos.encurtador.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.estudos.encurtador.dto.UrlRequest;
import com.estudos.encurtador.dto.UrlResponse;
import com.estudos.encurtador.services.UrlService;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class UrlController {

  @Autowired
  private UrlService urlService;
  
  @PostMapping("/shorten")
  public ResponseEntity<Object> create(@RequestBody UrlRequest urlRequest, HttpServletRequest servletRequest) {

    String originalUrl = urlRequest.url();
    String shortUrl = urlService.shorterUrl(originalUrl);

    String domain = servletRequest.getRequestURL().toString();
    var urlEncurtada = domain.replace("shorten", shortUrl);

    UrlResponse response = new UrlResponse(urlEncurtada);
    return ResponseEntity.ok(response);
  }
  
}
