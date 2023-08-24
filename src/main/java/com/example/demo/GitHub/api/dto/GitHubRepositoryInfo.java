package com.example.demo.GitHub.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GitHubRepositoryInfo(@JsonProperty("name") String repositoryName, GitHubOwnerResponse owner, List<GitHubBranchInfo> branches) {
}