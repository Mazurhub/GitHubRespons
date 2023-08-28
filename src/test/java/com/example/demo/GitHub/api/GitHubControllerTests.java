package com.example.demo.GitHub.api;

import com.example.demo.GitHub.api.dto.BranchesAndLastCommit;
import com.example.demo.GitHub.api.dto.Repositories;
import com.example.demo.GitHub.api.dto.RepositoriesInfo;
import com.example.demo.GitHub.api.exceptions.GitHubUserNotExisting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GitHubControllerTests {

    @MockBean // The dummy allows you to control the behavior of this component during tests.
    private GitHubFacade gitHubFacade;

    @Autowired // Mechanism for making HTTP requests on endpoint paths.
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {

        BranchesAndLastCommit lastCommit = new BranchesAndLastCommit("main", "abcdef123456");
        List<BranchesAndLastCommit> branches = Collections.singletonList(lastCommit);
        RepositoriesInfo repoInfo = new RepositoriesInfo("repoName", "ownerLogin", branches);
        Repositories repositories = new Repositories(Collections.singletonList(repoInfo));
        when(gitHubFacade.GetRepositoryInfoByUserName("validUser")).thenReturn(repositories);
    }

    @Test
    void testGetRepositoriesReturnsValidResponse() {
        String url = "http://localhost:" + port + "/user/validUser";
        ResponseEntity<String> response = new RestTemplate().getForEntity(url, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void testGetRepositoriesReturnsNotFoundForInvalidUser() {
        when(gitHubFacade.GetRepositoryInfoByUserName("invalidUser"))
                .thenThrow(new GitHubUserNotExisting("invalidUser: User not found or does not exist", null));

        ResponseEntity<String> response = restTemplate
                .getForEntity("http://localhost:" + port + "/user/invalidUser", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).contains("message", "invalidUser: User not found or does not exist");
    }

    @Test
    void testGetRepositoriesReturnsXmlResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList((MediaType.APPLICATION_XML)));

        ResponseEntity<String> response = restTemplate
                .exchange("http://localhost:" + port + "/user/validUser", HttpMethod.GET, new HttpEntity<>(headers), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_ACCEPTABLE);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).contains("Not Acceptable - Unsupported Media Type");
    }
}







