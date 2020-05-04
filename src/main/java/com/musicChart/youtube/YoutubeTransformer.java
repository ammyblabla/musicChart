package com.musicChart.youtube;

import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.google.api.services.youtube.model.VideoLocalization;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class YoutubeTransformer {
    public List<YoutubeDto> transformVideoList(VideoListResponse videoListResponse) {
        List<YoutubeDto> videos = new ArrayList();
        List<Video> items = videoListResponse.getItems();

        // TODO: Move iterate loop to service/job entry
        for (int i = 0; i < items.size(); i += 1) {
            videos.add(transformVideo(items.get(i), i + 1));
        }
        return videos;
    }

    public YoutubeDto transformVideo(Video video, int rank) {
        return YoutubeDto.builder()
                .rank(rank)
                .name(video.getSnippet().getTitle())
                .date(LocalDate.now())
                .id("id")
                .build();
    }

}
