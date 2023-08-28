package com.example.demo.GitHub;

import com.example.demo.GitHub.api.GitHubFacade;
import com.example.demo.GitHub.api.dto.Repositories;
import com.example.demo.GitHub.api.exceptions.GitHubUserNotExisting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GitHubFacadeImpl implements GitHubFacade {
    private final GetGitHubRepositoryInfoUseCase getGitHubRepositoryInfoUseCase;
    private static final Logger log = LoggerFactory.getLogger(GitHubFacadeImpl.class);

    GitHubFacadeImpl(GetGitHubRepositoryInfoUseCase getGitHubRepositoryInfoUseCase) {
        this.getGitHubRepositoryInfoUseCase = getGitHubRepositoryInfoUseCase;
    }

    /*
    The cache mechanism could have been utilized if we needed to perform multiple operations on the same data by storing them in short-term memory.
    Storing data in short-term memory using cache would have resulted in faster and more efficient application performance,
    by avoiding repetitive queries to the data source that rarely changes.
    In the current scenario, where queries are executed only once, implementing the cache mechanism is not necessary.
     */
    @Override
    public Repositories GetRepositoryInfoByUserName(String username) {
        try {
            return getGitHubRepositoryInfoUseCase.execute(username);
        } catch (Exception exception) {
            log.error("Error occurred", exception);
            throw new GitHubUserNotExisting(String.format(username + ": " + "User not found or does not exist"), exception);
        }
    }
}