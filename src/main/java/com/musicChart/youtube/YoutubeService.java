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
    YoutubeRepository youtubeRepository;

    @Autowired
    YoutubeTransformer youtubeTransformer;

    public List<YoutubeDto> handleRequest(String pageToken) throws IOException, GeneralSecurityException {
        VideoListResponse videoListResponse = youtubeRepository.getVideoListResponse(pageToken);
        return youtubeTransformer.transformVideoList(videoListResponse);
    }
}
