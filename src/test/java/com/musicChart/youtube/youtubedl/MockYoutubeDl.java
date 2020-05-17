package com.musicChart.youtube.youtubedl;

import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

import java.util.HashMap;
import java.util.Map;

public class MockYoutubeDl implements YoutubeDlService {
    private final BehaviorDelegate<YoutubeDlService> delegate;
    private final Map<String, YoutubeDlDto> ownYoutubeDl;

    MockYoutubeDl(BehaviorDelegate<YoutubeDlService> delegate) {
        this.delegate = delegate;
        ownYoutubeDl = new HashMap<String, YoutubeDlDto>();

        ownYoutubeDl.put("XGCWJoJb5W0",
                YoutubeDlDto.builder()
                        .artist("Three Man Down; Tilly Birds")
                        .track("Khwam Kid Thung Tee Chun Dai Khoei Song Pai Nai Kheun Tee Fon Proi Lhong Ma")
                        .build()
        );
    }

    @Override
    public Call<YoutubeDlDto> getTrackAndArtist(String videoId) {
        YoutubeDlDto response = ownYoutubeDl.get(videoId);
        return delegate.returningResponse(response).getTrackAndArtist(videoId);
    }
}
