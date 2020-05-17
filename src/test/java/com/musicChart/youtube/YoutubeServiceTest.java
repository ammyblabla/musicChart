package com.musicChart.youtube;

import com.google.api.services.youtube.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class YoutubeServiceTest {
    @InjectMocks
    YoutubeService youtubeService;

    @Mock
    YoutubeTransformer youtubeTransformer;

    @Mock
    YoutubeApiRepository youtubeApiRepository;

    @Test
    void should_invoke_youtubeRepository_and_youtubeTransformer_when_handle_request_given_receive_data() throws IOException, GeneralSecurityException {
        VideoListResponse expectedResult = getVideoResponse();
        given(youtubeApiRepository.getVideoListResponse(null)).willReturn(expectedResult);

        youtubeService.handleRequest(null);

        verify(youtubeApiRepository).getVideoListResponse(null);
        verify(youtubeTransformer).transformVideoList(expectedResult);
    }

    @Test
    void should_invoke_youtubeRepository_and_youtubeTransformer_when_handle_request_given_pageToken() throws IOException, GeneralSecurityException {
        VideoListResponse expectedResult = getVideoResponse();
        String pageToken = "CAUQAA";
        given(youtubeApiRepository.getVideoListResponse(pageToken)).willReturn(expectedResult);

        youtubeService.handleRequest(pageToken);

        verify(youtubeApiRepository).getVideoListResponse(pageToken);
        verify(youtubeTransformer).transformVideoList(expectedResult);
    }

    public static Video getVideo() {
        return new Video()
                .setKind("youtube#video")
                .setEtag("\"nxOHAKTVB7baOKsQgTtJIyGxcs8/mrzH_MEQhE0XqhOvcSR5iKcFtnw\"")
                .setId("ladClnnJhqg")
                .setSnippet(new VideoSnippet()
                        .setTitle("GOT7 \\\"NOT BY THE MOON\\\" M/V")
                )
                .setStatistics(new VideoStatistics()
                        .setViewCount(new BigInteger("1640541"))
                        .setLikeCount(new BigInteger("95981"))
                        .setDislikeCount(new BigInteger("760"))
                        .setFavoriteCount(new BigInteger("0"))
                        .setCommentCount(new BigInteger("2236"))
                );
    }

    public static VideoListResponse getVideoResponse() {
        return new VideoListResponse()
                .setKind("youtube#videoListResponse")
                .setEtag("\"nxOHAKTVB7baOKsQgTtJIyGxcs8/2kyQqE2LOQ5Kc9RUMi8e1xuKDtM\"")
                .setNextPageToken("CAUQAA")
                .setPageInfo(new PageInfo().setTotalResults(113).setResultsPerPage(1))
                .setItems(Collections.singletonList(getVideo())
                );
    }
}