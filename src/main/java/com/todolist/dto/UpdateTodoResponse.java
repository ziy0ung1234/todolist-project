package com.todolist.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateTodoResponse {
    private final Long id;
    private final String title;
    private final String username;
    private final LocalDateTime modifiedAt;

    public UpdateTodoResponse(Long id, String title, String username, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.modifiedAt = modifiedAt;
    }
}
