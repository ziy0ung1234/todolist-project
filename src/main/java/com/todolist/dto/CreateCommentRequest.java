package com.todolist.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private String content;
    private String username;
    private String password;
}
