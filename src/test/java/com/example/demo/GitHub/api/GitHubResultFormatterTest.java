package com.example.demo.GitHub.api;

import com.example.demo.GitHub.api.dto.GitHubBranchInfo;
import com.example.demo.GitHub.api.dto.GitHubCommitResponse;
import com.example.demo.GitHub.api.dto.GitHubOwnerResponse;
import com.example.demo.GitHub.api.dto.GitHubRepositoryInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;


class GitHubResultFormatterTest {


    private GitHubResultFormatter resultFormatter = new GitHubResultFormatter();

    @Test
    void testGenerateResponseWithFormattedData() {
        var branchInfo = new GitHubBranchInfo("master", new GitHubCommitResponse("commitSha"));
        var repoInfo = new GitHubRepositoryInfo("repoName", new GitHubOwnerResponse("ownerLogin"), Collections.singletonList(branchInfo));
        List<GitHubRepositoryInfo> repositoryInfoList = Collections.singletonList(repoInfo);

        List<ObjectNode> responses = resultFormatter.generateResponse(repositoryInfoList);

        assertThat(responses).isNotEmpty();
        ObjectNode response = responses.get(0);
        assertThat(response.get("Repository Name").asText()).isEqualTo("repoName");
        assertThat(response.get("Owner Login").asText()).isEqualTo("ownerLogin");

        JsonNode branchesNode = response.get("branches");
        assertThat(branchesNode.isArray()).isTrue();

        ArrayNode branchesArray = (ArrayNode) branchesNode;
        assertThat(branchesArray).hasSize(1);

        ObjectNode branchNode = (ObjectNode) branchesArray.get(0);
        assertThat(branchNode.get("Branch Name").asText()).isEqualTo("master");
        assertThat(branchNode.get("Last commit sha").asText()).isEqualTo("commitSha");
    }
}