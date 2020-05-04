package com.musicChart.youtube;

import com.google.api.services.youtube.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class YoutubeTransformerTest {
    @InjectMocks
    YoutubeTransformer youtubeTransformer;

    @Test
    void should_return_youtube_dto_list_when_transform_given_youtube_response() {
        VideoListResponse videoListResponse = getVideoResponse();
        List<YoutubeDto> expectedResult = Collections.singletonList(getYoutubeDto());

        List<YoutubeDto> actualResult = youtubeTransformer.transformVideoList(videoListResponse);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void should_return_youtube_dto_when_transform() {
        Video video = getVideo();
        YoutubeDto expectedResult = getYoutubeDto();

        YoutubeDto actualResult = youtubeTransformer.transformVideo(video,1);
        assertEquals(expectedResult,actualResult);
    }

    private Video getVideo() {
        VideoSnippet videoSnippet = new VideoSnippet().setTitle("GOT7 \\\"NOT BY THE MOON\\\" M/V");

        return new Video()
                .setKind("youtube#video")
                .setEtag("\"nxOHAKTVB7baOKsQgTtJIyGxcs8/mrzH_MEQhE0XqhOvcSR5iKcFtnw\"")
                .setId("ladClnnJhqg")
                .setSnippet(videoSnippet);

    }

    private VideoListResponse getVideoResponse() {
        return new VideoListResponse()
                .setKind("youtube#videoListResponse")
                .setEtag("\"nxOHAKTVB7baOKsQgTtJIyGxcs8/2kyQqE2LOQ5Kc9RUMi8e1xuKDtM\"")
                .setNextPageToken("CAUQAA")
                .setPageInfo(new PageInfo().setTotalResults(113).setResultsPerPage(1))
                .setItems(Collections.singletonList(getVideo())
                );
    }

    private YoutubeDto getYoutubeDto() {
        return new YoutubeDto().builder()
                .rank(1)
                .date(LocalDate.now())
                .name("GOT7 \\\"NOT BY THE MOON\\\" M/V")
                .id("id")
                .build();
    }


}