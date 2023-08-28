package com.example.demo.GitHub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GitHubClientTest {

    @Mock
    private GitHubClient gitHubClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRepositoryNameAndOwnerLoginByUserName() {
        // Arrange
        String username = "testUser";
        GitHubOwnerResponse owner = new GitHubOwnerResponse("ownerLogin");
        List<GitHubRepositoryInfo> expectedRepositories = Collections.singletonList(new GitHubRepositoryInfo("repo", owner, null));
        when(gitHubClient.getRepositoryNameAndOwnerLoginByUserName(username)).thenReturn(expectedRepositories);

        List<GitHubRepositoryInfo> result = gitHubClient.getRepositoryNameAndOwnerLoginByUserName(username);

        assertEquals(expectedRepositories.size(), result.size());

        GitHubRepositoryInfo expectedRepo = expectedRepositories.get(0);
        GitHubRepositoryInfo actualRepo = result.get(0);

        assertEquals(expectedRepo.name(), actualRepo.name());
        assertEquals(expectedRepo.owner().login(), actualRepo.owner().login());
    }

    @Test
    void testGetBranchesAndCommitsForRepository() {
        String owner = "testOwner";
        String repo = "testRepo";
        GitHubCommitResponse lastCommitSha = new GitHubCommitResponse("sha");
        List<GitHubBranchInfo> branches = Collections.singletonList(new GitHubBranchInfo("branch", lastCommitSha));

        when(gitHubClient.getBranchesAndCommitsForRepository(owner, repo)).thenReturn(branches);

        List<GitHubBranchInfo> result = gitHubClient.getBranchesAndCommitsForRepository(owner, repo);

        assertEquals(branches.size(), result.size());
        GitHubBranchInfo expectedBranch = branches.get(0);
        GitHubBranchInfo actualBranch = result.get(0);

        assertEquals(expectedBranch.name(), actualBranch.name());
        GitHubCommitResponse expectedCommit = expectedBranch.commit();
        GitHubCommitResponse actualCommit = actualBranch.commit();
        assertEquals(expectedCommit.sha(), actualCommit.sha());
    }
}




