package com.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteTodoRequest {
    @NotBlank(message="비밀번호는 필수 값 입니다.")
    private String password;
}
