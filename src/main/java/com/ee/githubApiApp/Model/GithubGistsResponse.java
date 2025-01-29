package com.ee.githubApiApp.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class GithubGistsResponse {

    @JsonProperty("id")
    private String id;

    //default constructor
    public GithubGistsResponse(){
    }

    //custom constructor
    public GithubGistsResponse(String id) {
        this.id = this.id;
    }
}
