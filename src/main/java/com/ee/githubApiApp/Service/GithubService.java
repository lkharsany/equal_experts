package com.ee.githubApiApp.Service;


import com.ee.githubApiApp.Model.GithubGistsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GithubService {
    private final WebClient webClient;

    @Autowired
    public GithubService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl("https://api.github.com").build();
    }

    public Mono<List<String>> getGists(String username,String accessToken){
        return webClient.get()
                .uri("/users/{username}/gists", username)
                .header("Authorization", "Bearer " + accessToken)
                .header("X-GitHub-Api-Version", "2022-11-28")
                .header("Accept", "application/vnd.github+json")
                .retrieve()
                .bodyToFlux(GithubGistsResponse.class)
                .map(GithubGistsResponse::getId)
                .collectList()
                .onErrorResume(error -> {
                    System.err.println("Error fetching gists: " + error.getMessage());

                    return Mono.just(List.of()); // Return an empty list on error
                });
    }
}
