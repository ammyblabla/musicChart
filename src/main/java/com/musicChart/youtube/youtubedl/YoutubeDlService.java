package com.musicChart.youtube.youtubedl;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface YoutubeDlService {
    @GET("{videoId}")
    Call<YoutubeDlDto> getTrackAndArtist(@Path("videoId") String videoId);
}
