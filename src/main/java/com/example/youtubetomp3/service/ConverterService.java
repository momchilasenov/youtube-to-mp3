package com.example.youtubetomp3.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Log4j2
public class ConverterService
{
  public String getSongIdFromUrl(String url)
  {
    Matcher matcher = getMatcher(url);
    String songId = "";

    if (matcher.find()) {
      songId = matcher.group();
    }
    else{
      log.debug(">>>>Could not extract song id from URL<<<<");
    }
    return songId;
  }

  private Matcher getMatcher(String url)
  {
    String pattern = "(?<=watch\\?v=|/videos/|embed/|youtu.be/|/v/|/e/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#&?\\n]*";

    Pattern compiledPattern = Pattern.compile(pattern);
    return compiledPattern.matcher(url);
  }
}
