package com.musicChart.youtube;

import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.google.api.services.youtube.model.VideoStatistics;
import com.musicChart.youtube.youtubedl.YoutubeDlRepository;
import com.musicChart.youtube.youtubedl.YoutubeDlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class YoutubeTransformer {
    @Autowired
    YoutubeDlRepository youtubeDlRepository;

    public List<YoutubeDto> transformVideoList(VideoListResponse videoListResponse) throws IOException {
        List<YoutubeDto> videos = new ArrayList();
        List<Video> items = videoListResponse.getItems();

        // TODO: Move iterate loop to service/job entry
        for (int i = 0; i < items.size(); i += 1) {
            Video video = items.get(i);
            YoutubeDlDto youtubeDlDto = youtubeDlRepository.getTrackAndArtist(video.getId());
            videos.add(transformVideo(video, youtubeDlDto,i + 1));
        }
        return videos;
    }

    public YoutubeDto transformVideo(Video video, YoutubeDlDto youtubeDlDto, int rank) {
        return YoutubeDto.builder()
                .rank(rank)
                .title(video.getSnippet().getTitle())
                .date(LocalDate.now())
                .id(video.getId())
                .statistics(transformStatistics(video.getStatistics()))
                .track(youtubeDlDto.getTrack())
                .artist(youtubeDlDto.getArtist())
                .build();
    }

    public YoutubeDto.Statistics transformStatistics(VideoStatistics videoStatistics) {
        return YoutubeDto.Statistics.builder()
                .viewCount(videoStatistics.getViewCount())
                .likeCount(videoStatistics.getLikeCount())
                .dislikeCount(videoStatistics.getDislikeCount())
                .favouriteCount(videoStatistics.getFavoriteCount())
                .commentCount(videoStatistics.getCommentCount())
                .build();
    }
}
