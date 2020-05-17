package com.musicChart.youtube.youtubedl;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Service
public class YoutubeDlRepository {
    public static final String API_URL = "http://127.0.0.1:5002/youtube/";

    public YoutubeDlDto getTrackAndArtist(String videoId) throws IOException {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

        YoutubeDlService youtubeDlService = retrofit.create(YoutubeDlService.class);
        Call<YoutubeDlDto> call = youtubeDlService.getTrackAndArtist(videoId);
        Response response = call.execute();
        return (YoutubeDlDto) response.body();
    }
}
