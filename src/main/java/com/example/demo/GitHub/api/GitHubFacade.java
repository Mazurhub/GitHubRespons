package com.example.demo.GitHub.api;


import com.example.demo.GitHub.api.dto.Repositories;


public interface GitHubFacade {

    Repositories GetRepositoryInfoByUserName(String username);
}