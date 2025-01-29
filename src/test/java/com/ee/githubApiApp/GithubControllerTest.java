package com.ee.githubApiApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GithubControllerTest {

    private String BASE_URL = "http://localhost:8080";

    @Autowired
    private MockMvc mockMvc;


    public GithubControllerTest() {
    }

    @Test
    public void testGetGistsForKnownUser() throws Exception {

        // Act
        ResultActions result =mockMvc.perform(get("/github/gists")
                .param("username", "octocat")
                .accept(MediaType.APPLICATION_JSON));
        System.out.println(result);

        // Assert

        // Assert
        result.andExpect(status().is3xxRedirection());

    }
}