package com.todolist.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetOneTodoResponse {
    private final Long todoId;
    private final String username;
    private final String title;
    private final String description;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetOneTodoResponse(Long todoId, String username, String title, String description, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.todoId = todoId;
        this.username = username;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
