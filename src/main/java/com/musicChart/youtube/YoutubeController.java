package com.musicChart.youtube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
public class YoutubeController {
    @Autowired
    YoutubeService youtubeService;

    @GetMapping(value="/youtubeRank")
    public List<YoutubeDto> should_return_youtube_dto_when_call_controller() throws IOException, GeneralSecurityException {
        return youtubeService.handleRequest();
    }
}
