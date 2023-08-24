package com.example.demo.GitHub.api;

import com.example.demo.GitHub.api.dto.GitHubBranchInfo;
import com.example.demo.GitHub.api.dto.GitHubRepositoryInfo;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class GitHubResultFormatter {

    List<ObjectNode> generateResponse(List<GitHubRepositoryInfo> repositoryInfoList) {
        List<ObjectNode> responses = new ArrayList<>();
        for (GitHubRepositoryInfo repoInfo : repositoryInfoList) {
            ArrayNode branchArray = JsonNodeFactory.instance.arrayNode();
            for (GitHubBranchInfo branchInfo : repoInfo.branches()) {
                ObjectNode branchNode = JsonNodeFactory.instance.objectNode();
                branchNode.put("Branch Name", branchInfo.name());
                branchNode.put("Last commit sha", branchInfo.commit().sha());
                branchArray.add(branchNode);
            }
            ObjectNode response = JsonNodeFactory.instance.objectNode();
            response.put("Repository Name", repoInfo.repositoryName());
            response.put("Owner Login", repoInfo.owner().login());
            response.set("branches", branchArray);

            responses.add(response);
        }
        return responses;
    }
}

