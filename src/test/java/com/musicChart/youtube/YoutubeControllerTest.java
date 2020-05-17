package com.musicChart.youtube;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.musicChart.FileUtils.readFileAsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class YoutubeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    YoutubeService youtubeService;

    @Test
    void should_return_Youtube_Dto_when_call_controller() throws Exception {
        List<YoutubeDto> youtubeDto = getYoutubeDto();
        given(youtubeService.handleRequest(null)).willReturn(youtubeDto);
        String expectedResult = readFileAsString("response.json");

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/youtubeRank")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    void should_return_Youtube_Dto_when_call_controller_given_pageToken() throws Exception {
        List<YoutubeDto> youtubeDto = getYoutubeDto();
        given(youtubeService.handleRequest("CAUQAA")).willReturn(youtubeDto);
        String expectedResult = readFileAsString("response.json");

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/youtubeRank/pageToken/CAUQAA")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedResult));

    }

    private List<YoutubeDto> getYoutubeDto() {
        return Collections.singletonList(YoutubeDto.builder()
                .rank(1)
                .title("ความคิดถึงที่ฉันได้เคยส่งไปในคืนที่ฝนโปรยลงมา - Three Man Down x Tilly Birds")
                .date(LocalDate.of(2018,12,18))
                .id("XGCWJoJb5W0")
                .statistics(new YoutubeDto.Statistics().builder()
                        .viewCount(new BigInteger("1648467"))
                        .likeCount(new BigInteger("96043"))
                        .dislikeCount(new BigInteger("761"))
                        .favouriteCount(new BigInteger("0"))
                        .commentCount(new BigInteger("2237"))
                        .build())
                .track("Khwam Kid Thung Tee Chun Dai Khoei Song Pai Nai Kheun Tee Fon Proi Lhong Ma")
                .artist("Three Man Down; Tilly Birds")
                .build());
    }
}