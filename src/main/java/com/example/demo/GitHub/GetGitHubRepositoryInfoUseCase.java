package com.example.demo.GitHub;

import com.example.demo.GitHub.api.dto.BranchesAndLastCommit;
import com.example.demo.GitHub.api.dto.Repositories;
import com.example.demo.GitHub.api.dto.RepositoriesInfo;

import java.util.List;
import java.util.stream.Collectors;

class GetGitHubRepositoryInfoUseCase {
    private final GitHubRepositoryConnector connector;

    GetGitHubRepositoryInfoUseCase(GitHubRepositoryConnector connector) {
        this.connector = connector;
    }

    Repositories execute(String username) {
        List<GitHubRepositoryInfo> repositories = connector.getRepositoryNameAndOwnerLoginByUserName(username);

        List<RepositoriesInfo> repositoriesInfo = repositories.stream()
                .map(this::mapToRepositoriesInfo)
                .collect(Collectors.toList());

        return new Repositories(repositoriesInfo);
    }

    private RepositoriesInfo mapToRepositoriesInfo(GitHubRepositoryInfo repo) {
        String repoName = repo.name();
        String owner = repo.owner().login();

        List<GitHubBranchInfo> branchesAndCommits = connector.getBranchesAndCommitsForRepository(owner, repoName);
        List<BranchesAndLastCommit> mappedBranches = mapToBranchesAndLastCommits(branchesAndCommits);

        return new RepositoriesInfo(repoName, owner, mappedBranches);
    }

    private List<BranchesAndLastCommit> mapToBranchesAndLastCommits(List<GitHubBranchInfo> branchesAndCommits) {
        return branchesAndCommits.stream()
                .map(branch -> new BranchesAndLastCommit(branch.name(), branch.commit().sha()))
                .collect(Collectors.toList());
    }
}
/*
List<GitHubRepositoryInfo> repositories = connector.getRepositoryNameAndOwnerLoginByUserName(username);

        List<RepositoriesInfo> repositoriesInfo = new ArrayList<>();

        for (int i = 0; i < repositories.size(); i++) {
            GitHubRepositoryInfo repo = repositories.get(i);
            String repoName = repo.name();
            String owner = repo.owner().login();
            List<GitHubBranchInfo> branchesAndCommits = connector.getBranchesAndCommitsForRepository(owner, repoName);

            List<BranchesAndLastCommit> updatedBranches = new ArrayList<>();

            for (int b = 0; b < branchesAndCommits.size(); b++) {
                GitHubBranchInfo branch = branchesAndCommits.get(b);
                String branchName = branch.name();
                String lastCommitSha = branch.commit().sha();

                BranchesAndLastCommit updateBranch = new BranchesAndLastCommit(branchName, lastCommitSha);
                updatedBranches.add(updateBranch);
            }

            RepositoriesInfo newRepositoriesInfo = new RepositoriesInfo(repoName, owner, updatedBranches);
            repositoriesInfo.add(newRepositoriesInfo);
        }

        return repositoriesInfo;
 */