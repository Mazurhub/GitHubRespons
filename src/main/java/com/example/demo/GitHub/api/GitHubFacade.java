package com.example.demo.GitHub.api;

import com.example.demo.GitHub.api.dto.GitHubRepositoryInfo;

import java.util.List;


public interface GitHubFacade {

    List<GitHubRepositoryInfo> GetRepositoryInfoByUserName(String username);
}