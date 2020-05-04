package com.musicChart.youtube;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.VideoListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Repository
public class YoutubeRepository {
    @Autowired
    private YoutubeConfiguration youtubeConfiguration;
//    private YoutubeConfiguration youtubeConfiguration = new YoutubeConfiguration();
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public static YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport, JSON_FACTORY, null)
                .setApplicationName("musicChart")
                .build();
    }

    public VideoListResponse getVideoListResponse() throws IOException, GeneralSecurityException {
        YouTube.Videos.List youtubeRequest = getService().videos().list("snippet,localizations");
        System.out.println("key " + youtubeConfiguration.getDeveloperKey());
        return youtubeRequest.setKey(youtubeConfiguration.getDeveloperKey())
                .setChart("mostPopular")
                .setRegionCode("TH")
                .setVideoCategoryId("10")
                .execute();
    }


}
