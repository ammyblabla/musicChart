package com.musicChart.youtube;

import com.google.api.services.youtube.YouTube;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class YoutubeApiRepositoryTest {
    @InjectMocks
    YoutubeApiRepository youtubeApiRepository;

    @Test
    void should_get_youtube_when_get_service() throws GeneralSecurityException, IOException {
        assertTrue(youtubeApiRepository.getService() instanceof YouTube);
    }
}
