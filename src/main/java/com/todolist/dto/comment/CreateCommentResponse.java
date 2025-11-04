package com.todolist.dto.comment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {
    private final Long id;
    private final String content;
    private final String username;
    private final String password;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    public CreateCommentResponse(Long id, String content, String username, String password, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.content = content;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
