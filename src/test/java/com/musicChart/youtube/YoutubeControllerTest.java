package com.musicChart.youtube;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        given(youtubeService.handleRequest()).willReturn(youtubeDto);
        String expectedResult = "[{\"date\":\"2018-12-18\",\"rank\":2,\"name\":\"song name\",\"id\":\"id\"}]";

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/youtubeRank")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    private List<YoutubeDto> getYoutubeDto() {
        return Collections.singletonList(YoutubeDto.builder()
                .rank(2)
                .name("song name")
                .date(LocalDate.of(2018,12,18))
                .id("id")
                .build());
    }
}