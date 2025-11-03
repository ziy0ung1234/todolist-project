package com.todolist.dto;

import lombok.Getter;

@Getter
public class CreateTodoRequest {
    private String title;
    private String description;
    private Long userId;
}
