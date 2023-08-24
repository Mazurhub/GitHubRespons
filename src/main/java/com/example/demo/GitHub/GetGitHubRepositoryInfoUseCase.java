package com.example.demo.GitHub;

import com.example.demo.GitHub.api.dto.GitHubBranchInfo;
import com.example.demo.GitHub.api.dto.GitHubCommitResponse;
import com.example.demo.GitHub.api.dto.GitHubRepositoryInfo;

import java.util.ArrayList;
import java.util.List;

class GetGitHubRepositoryInfoUseCase {
    private final GitHubRepositoryConnector connector;

    GetGitHubRepositoryInfoUseCase(GitHubRepositoryConnector connector) {
        this.connector = connector;
    }

    public List<GitHubRepositoryInfo> execute(String username) {
        List<GitHubRepositoryInfo> repositories = connector.getRepositoryNameAndOwnerLoginByUserName(username);

        for (int i = 0; i < repositories.size(); i++) {
            GitHubRepositoryInfo repo = repositories.get(i);
            String repoName = repo.repositoryName();
            String owner = repo.owner().login();
            List<GitHubBranchInfo> branchesAndCommits = connector.getBranchesAndCommitsForRepository(owner, repoName);

            List<GitHubBranchInfo> updatedBranches = new ArrayList<>();

            for (int b = 0; b < branchesAndCommits.size(); b++) {
                GitHubBranchInfo branch = branchesAndCommits.get(b);
                GitHubCommitResponse commitResponse = branch.commit();
                String commitSha = commitResponse.sha();

                GitHubCommitResponse updatedCommitResponse = new GitHubCommitResponse(commitSha);
                GitHubBranchInfo updatedBranch = new GitHubBranchInfo(branch.name(), updatedCommitResponse);
                updatedBranches.add(updatedBranch);
            }

            GitHubRepositoryInfo updatedRepo = new GitHubRepositoryInfo(repoName, repo.owner(), updatedBranches);
            repositories.set(i, updatedRepo);
        }

        return repositories;
    }
}

/*
The same, but written with a foreach and stream loop.
Personally, I find the standard loop to be more readable.

public List<GitHubRepositoryInfo> execute(String username) {
        List<GitHubRepositoryInfo> repositories = connector.getRepositoryNameAndOwnerLoginByUserName(username);

        repositories.forEach(repo -> {
            String repoName = repo.repositoryName();
            String owner = repo.owner().login();
            List<GitHubBranchInfo> branchesAndCommits = connector.getBranchesAndCommitsForRepository(owner, repoName);

            List<GitHubBranchInfo> updatedBranches = branchesAndCommits.stream()
                    .map(branch -> {
                        GitHubCommitResponse commitResponse = branch.commit();
                        String commitSha = commitResponse.sha();

                        GitHubCommitResponse updatedCommitResponse = new GitHubCommitResponse(commitSha);
                        GitHubBranchInfo updatedBranch = new GitHubBranchInfo(branch.name(), updatedCommitResponse);
                        return updatedBranch;
                    })
                    .collect(Collectors.toList());

            GitHubRepositoryInfo updatedRepo = new GitHubRepositoryInfo(repoName, repo.owner(), updatedBranches);
            repositories.set(repositories.indexOf(repo), updatedRepo);
        });

        return repositories;
    }
}
 */

