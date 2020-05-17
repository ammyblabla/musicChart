package com.musicChart.youtube.youtubedl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static org.junit.jupiter.api.Assertions.assertEquals;

class YoutubeDlTest {
    private static final String API_URL = "http://127.0.0.1:5002/youtube/";
    private final NetworkBehavior behavior = NetworkBehavior.create();
    private MockYoutubeDl mockYoutubeDl;

    @BeforeEach
    public void setUp() {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior).build();

        final BehaviorDelegate<YoutubeDlService> delegate = mockRetrofit.create(YoutubeDlService.class);
        mockYoutubeDl = new MockYoutubeDl(delegate);
    }

    @Test
    void should_return_track_artist_when_request_given_video_id() throws Exception {
        YoutubeDlDto expectedResult = YoutubeDlDto.builder()
                .artist("Three Man Down; Tilly Birds")
                .track("Khwam Kid Thung Tee Chun Dai Khoei Song Pai Nai Kheun Tee Fon Proi Lhong Ma")
                .build();

        Call<YoutubeDlDto> call = mockYoutubeDl.getTrackAndArtist("XGCWJoJb5W0");
        YoutubeDlDto actualResult = call.execute().body();

        assertEquals(expectedResult, actualResult);
    }
}