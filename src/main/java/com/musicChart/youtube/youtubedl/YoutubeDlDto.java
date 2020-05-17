package com.musicChart.youtube.youtubedl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class YoutubeDlDto {
    String artist;
    String track;
}
