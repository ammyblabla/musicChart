package com.musicChart.youtube;

import com.google.api.services.youtube.model.*;
import com.musicChart.youtube.youtubedl.YoutubeDlDto;
import com.musicChart.youtube.youtubedl.YoutubeDlRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class YoutubeTransformerTest {
    @InjectMocks
    YoutubeTransformer youtubeTransformer;

    @Mock
    YoutubeDlRepository youtubeDlRepository;

    @Test
    void should_return_youtube_dto_list_when_transform_given_youtube_response() throws IOException {
        VideoListResponse videoListResponse = getVideoResponse();
        List<YoutubeDto> expectedResult = Collections.singletonList(getYoutubeDto());
        given(youtubeDlRepository.getTrackAndArtist("ladClnnJhqg"))
                .willReturn(YoutubeDlDto.
                        builder()
                        .artist("GOT7")
                        .track("NOT BY THE MOON")
                        .build()
                );

        List<YoutubeDto> actualResult = youtubeTransformer.transformVideoList(videoListResponse);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void should_return_youtube_dto_when_transform() {
        Video video = getVideo();
        YoutubeDto expectedResult = getYoutubeDto();

        YoutubeDto actualResult = youtubeTransformer.transformVideo(video,getYoutubeDlDto(),1);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void should_return_statistics_when_transform() {
        VideoStatistics videoStatistics = getVideoStatistics();
        assertEquals(getStatisticsDto(), youtubeTransformer.transformStatistics(videoStatistics));
    }

    private Video getVideo() {
        VideoSnippet videoSnippet = new VideoSnippet().setTitle("GOT7 \\\"NOT BY THE MOON\\\" M/V");

        return new Video()
                .setKind("youtube#video")
                .setEtag("\"nxOHAKTVB7baOKsQgTtJIyGxcs8/mrzH_MEQhE0XqhOvcSR5iKcFtnw\"")
                .setId("ladClnnJhqg")
                .setSnippet(videoSnippet)
                .setStatistics(getVideoStatistics());
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
                .title("GOT7 \\\"NOT BY THE MOON\\\" M/V")
                .id("ladClnnJhqg")
                .statistics(getStatisticsDto())
                .artist("GOT7")
                .track("NOT BY THE MOON")
                .build();
    }

    private VideoStatistics getVideoStatistics() {
        return new VideoStatistics()
                .setViewCount(new BigInteger("1640541"))
                .setLikeCount(new BigInteger("95981"))
                .setDislikeCount(new BigInteger("760"))
                .setFavoriteCount(new BigInteger("0"))
                .setCommentCount(new BigInteger("2236"));
    }

    private YoutubeDto.Statistics getStatisticsDto() {
        return new YoutubeDto.Statistics().builder()
                .viewCount(new BigInteger("1640541"))
                .likeCount(new BigInteger("95981"))
                .dislikeCount(new BigInteger("760"))
                .favouriteCount(new BigInteger("0"))
                .commentCount(new BigInteger("2236"))
                .build();
    }

    private YoutubeDlDto getYoutubeDlDto() {
        return YoutubeDlDto.builder()
                .artist("GOT7")
                .track("NOT BY THE MOON")
                .build();
    }

}