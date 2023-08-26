package com.example.demo.GitHub;

import com.example.demo.GitHub.api.GitHubFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class GitHubConfiguration {


    @Bean
    GetGitHubRepositoryInfoUseCase getGitHubRepositoryInfoUseCase(GitHubRepositoryConnector gitHubRepositoryConnector) {
        return new GetGitHubRepositoryInfoUseCase(gitHubRepositoryConnector);
    }

    @Bean
    GitHubFacade gitHubFacade(GetGitHubRepositoryInfoUseCase getGitHubRepositoryInfoUseCase) {
        return new GitHubFacadeImpl(getGitHubRepositoryInfoUseCase);
    }
}