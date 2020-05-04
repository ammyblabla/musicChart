package com.musicChart.youtube;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YoutubeDto {
    LocalDate date;
    int rank;
    String name;
    String id;
}
