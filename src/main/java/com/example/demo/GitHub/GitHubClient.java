package com.example.demo.GitHub;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "GitHubClient", url = "https://api.github.com/")
interface GitHubClient extends GitHubRepositoryConnector {
    @RequestMapping(method = RequestMethod.GET, value = "users/{username}/repos", produces = "application/json")
    @Override
    List<GitHubRepositoryInfo> getRepositoryNameAndOwnerLoginByUserName(@PathVariable("username") String username);

    @RequestMapping(method = RequestMethod.GET, value = "repos/{owner}/{repo}/branches", produces = "application/json")
    @Override
    List<GitHubBranchInfo> getBranchesAndCommitsForRepository(@PathVariable("owner") String owner, @PathVariable("repo") String repo);
}