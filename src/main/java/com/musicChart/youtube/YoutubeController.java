package com.musicChart.youtube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
public class YoutubeController {
    @Autowired
    YoutubeService youtubeService;

    // TODO: Make endpoint for collect all page

    @GetMapping(value="/youtubeRank", produces = "application/json;charset=UTF-8")
    public List<YoutubeDto> getYoutubeChart() throws IOException, GeneralSecurityException {
        return youtubeService.handleRequest(null);
    }


    @GetMapping(value="/youtubeRank/pageToken/{pageToken}", produces = "application/json;charset=UTF-8")
    public List<YoutubeDto> getYoutubeChartByPageToken (@PathVariable String pageToken) throws IOException, GeneralSecurityException {
        return youtubeService.handleRequest(pageToken);
    }

}
