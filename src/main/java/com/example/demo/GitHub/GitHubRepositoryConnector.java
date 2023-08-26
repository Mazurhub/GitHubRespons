package com.example.demo.GitHub;

import com.example.demo.GitHub.api.dto.GitHubBranchInfo;
import com.example.demo.GitHub.api.dto.GitHubRepositoryInfo;

import java.util.List;

interface GitHubRepositoryConnector {
    List<GitHubRepositoryInfo> getRepositoryNameAndOwnerLoginByUserName(String username);

    List<GitHubBranchInfo> getBranchesAndCommitsForRepository(String owner, String repo);
}