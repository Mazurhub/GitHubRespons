package com.example.demo.GitHub;

import com.example.demo.GitHub.api.GitHubFacade;
import com.example.demo.GitHub.api.dto.GitHubRepositoryInfo;
import com.example.demo.GitHub.api.exceptions.GitHubUserNotExisting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

class GitHubFacadeImpl implements GitHubFacade {
    private final GetGitHubRepositoryInfoUseCase getGitHubRepositoryInfoUseCase;
    private static final Logger log = LoggerFactory.getLogger(GitHubFacadeImpl.class);

    GitHubFacadeImpl(GetGitHubRepositoryInfoUseCase getGitHubRepositoryInfoUseCase) {
        this.getGitHubRepositoryInfoUseCase = getGitHubRepositoryInfoUseCase;
    }

    @Override
    public List<GitHubRepositoryInfo> GetRepositoryInfoByUserName(String username) {
        try {
            return getGitHubRepositoryInfoUseCase.execute(username);
        } catch (Exception exception) {
            log.error("Error occurred", exception);
            throw new GitHubUserNotExisting(String.format(username + ": " + "User not found or does not exist"), exception);
        }
    }
}