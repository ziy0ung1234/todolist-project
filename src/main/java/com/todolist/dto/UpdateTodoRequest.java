package com.todolist.dto;

import lombok.Getter;

@Getter
public class UpdateTodoRequest {
    private String title;
    private String username;
    private String password;
}
