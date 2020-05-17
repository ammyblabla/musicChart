package com.musicChart.youtube;

import com.google.api.services.youtube.model.VideoListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class YoutubeService {
    // TODO: save each video in mongo
    // TODO: add field in dto
    @Autowired
    YoutubeApiRepository youtubeApiRepository;

    @Autowired
    YoutubeTransformer youtubeTransformer;

    public List<YoutubeDto> handleRequest(String pageToken) throws IOException, GeneralSecurityException {
        VideoListResponse videoListResponse = youtubeApiRepository.getVideoListResponse(pageToken);
        return youtubeTransformer.transformVideoList(videoListResponse);
    }
}
