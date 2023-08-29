package com.example.demo.GitHub;

import com.example.demo.GitHub.api.GitHubFacade;
import com.example.demo.GitHub.api.dto.BranchesAndLastCommit;
import com.example.demo.GitHub.api.dto.Repositories;
import com.example.demo.GitHub.api.dto.RepositoriesInfo;
import com.example.demo.GitHub.api.exceptions.GitHubUserNotExisting;
import feign.FeignException;
import feign.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GitHubFacadeTest {
    private GitHubFacade gitHubFacade;
    private GetGitHubRepositoryInfoUseCase getGitHubRepositoryInfoUseCase;
    @Mock
    private GitHubRepositoryConnector connector;

    @BeforeEach
    void setup() {
        getGitHubRepositoryInfoUseCase = new GetGitHubRepositoryInfoUseCase(connector);
        gitHubFacade = new GitHubFacadeImpl(getGitHubRepositoryInfoUseCase);

    }

    @Test
    void getRepositoriesByValidUserName() {
        //GIVEN
        String userName = "validUserName";
        String repositoryName = "repoName";
        String ownerLogin = "ownerLogin";
        GitHubOwnerResponse owner = new GitHubOwnerResponse(ownerLogin);
        GitHubRepositoryInfo givenRepository = new GitHubRepositoryInfo(repositoryName, owner);
        List<GitHubRepositoryInfo> givenRepositories = List.of(givenRepository);
        when(connector.getRepositoryNameAndOwnerLoginByUserName(userName)).thenReturn(givenRepositories);

        String givenBranchName = "branchName";
        String shaCommit = "shaCommit";
        GitHubCommitResponse givenCommitSha = new GitHubCommitResponse(shaCommit);
        GitHubBranchInfo givenBranch = new GitHubBranchInfo(givenBranchName, givenCommitSha);
        List<GitHubBranchInfo> givenBranches = List.of(givenBranch);
        when(connector.getBranchesAndCommitsForRepository(owner.login(), repositoryName)).thenReturn(givenBranches);

        //WHEN
        when(connector.getRepositoryNameAndOwnerLoginByUserName(userName)).thenReturn(givenRepositories);
        var responseRepositories = gitHubFacade.getRepositoryInfoByUserName(userName);

        //THEN
        BranchesAndLastCommit branchesAndLastCommit = new BranchesAndLastCommit(givenBranchName, shaCommit);
        RepositoriesInfo repositoriesInfo = new RepositoriesInfo(repositoryName, ownerLogin, List.of(branchesAndLastCommit));
        Repositories repositories = new Repositories(List.of(repositoriesInfo));
        assertThat(responseRepositories).usingRecursiveComparison().isEqualTo(repositories);
    }

    @Test
    void shouldThrowExceptionForNonExistingGitHubUser() {
        // GIVEN
        String userName = "invalidUserName";
        Request request = mock(Request.class);
        when(connector.getRepositoryNameAndOwnerLoginByUserName(userName)).thenThrow(new FeignException.NotFound("", request, null, null));

        // WHEN
        try {
            gitHubFacade.getRepositoryInfoByUserName(userName);
            fail(userName + ": " + "User not found or does not exist");
        } catch (GitHubUserNotExisting e) {
            // THEN
            assertThat(e.getMessage()).isEqualTo(userName + ": " + "User not found or does not exist");
        }
    }
}