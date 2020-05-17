package com.musicChart.youtube;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YoutubeDto {
    LocalDate date;
    int rank;
    String title;
    String id;
    Statistics statistics;
    String track;
    String artist;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Statistics {
        BigInteger viewCount;
        BigInteger likeCount;
        BigInteger dislikeCount;
        BigInteger favouriteCount;
        BigInteger commentCount;
    }
}
