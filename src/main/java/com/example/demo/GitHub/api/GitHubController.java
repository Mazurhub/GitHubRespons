package com.example.demo.GitHub.api;

import com.example.demo.GitHub.api.dto.RepositoriesInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
class GitHubController {
    private final GitHubFacade gitHubFacade;

    GitHubController(GitHubFacade gitHubFacade) {
        this.gitHubFacade = gitHubFacade;
    }

    @GetMapping(value = "/{username}", produces = "application/json")
    List<RepositoriesInfo> getRepositoriesInfo(@PathVariable String username) {
        return gitHubFacade.GetRepositoryInfoByUserName(username);

    }
}
