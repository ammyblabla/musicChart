package com.musicChart.youtube;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.VideoListResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class YoutubeRepositoryTest {
    @InjectMocks
    YoutubeRepository youtubeRepository;

    @Test
    void should_get_youtube_when_get_service() throws GeneralSecurityException, IOException {
        assertTrue(youtubeRepository.getService() instanceof YouTube);
    }
}
