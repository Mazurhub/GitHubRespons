package com.example.demo.GitHub;

import java.util.List;

interface GitHubRepositoryConnector {
    List<GitHubRepositoryInfo> getRepositoryNameAndOwnerLoginByUserName(String username);

    List<GitHubBranchInfo> getBranchesAndCommitsForRepository(String owner, String repo);
}