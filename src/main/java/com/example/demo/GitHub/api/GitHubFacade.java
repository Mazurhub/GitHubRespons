package com.example.demo.GitHub.api;


import com.example.demo.GitHub.GitHubRepositoryInfo;
import com.example.demo.GitHub.api.dto.RepositoriesInfo;

import java.util.List;


public interface GitHubFacade {

    List<RepositoriesInfo> GetRepositoryInfoByUserName(String username);
}