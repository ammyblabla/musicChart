package com.musicChart.youtube.youtubedl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YoutubeDlIntegrationTest {
    private static final String API_URL = "http://127.0.0.1:5002/youtube/";
    YoutubeDlService youtubeDlService;

    @BeforeEach
    void setup() {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

        youtubeDlService = retrofit.create(YoutubeDlService.class);
    }

    @Test
    void should_return_track_artist_when_request_given_video_id() throws Exception {
        YoutubeDlDto expectedResult = YoutubeDlDto.builder()
                .artist("Three Man Down; Tilly Birds")
                .track("Khwam Kid Thung Tee Chun Dai Khoei Song Pai Nai Kheun Tee Fon Proi Lhong Ma")
                .build();

        YoutubeDlDto actualResult = youtubeDlService.getTrackAndArtist("XGCWJoJb5W0").execute().body();

        assertEquals(expectedResult, actualResult);

    }
}
