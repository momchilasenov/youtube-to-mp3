package com.example.youtubetomp3.controller;

import com.example.youtubetomp3.service.ConverterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ConverterController
{
  private final String DOWNLOAD = "/download";

  private final ConverterService converterService;

  @GetMapping(DOWNLOAD)
  public ResponseEntity<String> redirectToExternalAPI(@RequestParam String url) throws URISyntaxException
  {
    String songId = converterService.getSongIdFromUrl(url);

    String EXTERNAL_API = "https://api.vevioz.com/api/button/mp3/";
    URI uriToRedirect = new URI(EXTERNAL_API + songId);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setLocation(uriToRedirect);
    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
  }
}
