package com.ee.githubApiApp.Controller;


import com.ee.githubApiApp.Service.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/github")
public class GithubController {

    private final GithubService githubService;

    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @Autowired
    public GithubController(GithubService githubService, OAuth2AuthorizedClientService oAuth2AuthorizedClientService){
        this.githubService = githubService;
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }

    @GetMapping("/gists")
    public Mono<List<String>> getGists(
            @RequestParam String username,
            OAuth2AuthenticationToken authenticationToken){

        String accessToken = getAccessToken(authenticationToken);
        return githubService.getGists(username, accessToken);
    }

    private String getAccessToken(OAuth2AuthenticationToken authenticationToken){
        OAuth2AuthorizedClient client = oAuth2AuthorizedClientService.loadAuthorizedClient("github", authenticationToken.getName());

        if(client != null){
            OAuth2AccessToken accessToken = client.getAccessToken();

            if (accessToken != null){
                return  accessToken.getTokenValue();
            }
        }
        throw new RuntimeException("Failed to retrieve access token from GitHub");
    }
}
