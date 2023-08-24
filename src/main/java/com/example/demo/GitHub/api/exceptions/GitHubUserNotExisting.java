package com.example.demo.GitHub.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GitHubUserNotExisting extends RuntimeException{
    public GitHubUserNotExisting(String message, Throwable cause) {
        super(message, cause);
    }
}
