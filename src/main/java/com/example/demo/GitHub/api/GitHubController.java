package com.example.demo.GitHub.api;

import com.example.demo.GitHub.api.dto.GitHubRepositoryInfo;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
class GitHubController {
    private final GitHubFacade gitHubFacade;
    private final GitHubResultFormatter gitHubResultFormatter;

    GitHubController(GitHubFacade gitHubFacade, GitHubResultFormatter gitHubResultFormatter) {
        this.gitHubFacade = gitHubFacade;
        this.gitHubResultFormatter = gitHubResultFormatter;
    }

    @GetMapping(value = "/{username}", produces = "application/json")
    List<ObjectNode> getRepositories(@PathVariable String username) {
        List<GitHubRepositoryInfo> repositoryInfo = gitHubFacade.GetRepositoryInfoByUserName(username);
        return gitHubResultFormatter.generateResponse(repositoryInfo);
    }
}
