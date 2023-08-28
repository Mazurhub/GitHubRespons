package com.example.demo.GitHub.api.dto;

import java.util.List;

public record RepositoriesInfo (String repositoryName, String ownerLogin, List<BranchesAndLastCommit> branches) {
}
