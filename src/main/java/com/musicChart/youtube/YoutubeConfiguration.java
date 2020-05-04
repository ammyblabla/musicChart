package com.musicChart.youtube;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "youtube")
@EnableConfigurationProperties
@Getter
@Setter
@NoArgsConstructor
public class YoutubeConfiguration {
    private String developerKey;
    private String applicationName;
    private String value;
}
