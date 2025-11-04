package com.todolist.dto.todo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CreateTodoRequest {
    @NotBlank(message="제목은 필수 값입니다.")
    @Size(max=30)
    private String title;
    @NotBlank(message="일정 내용은 필수 값입니다.")
    @Size(max=200)
    private String description;
    @NotBlank(message="이름은 필수 값입니다.")
    private String username;
    @NotBlank(message="비밀번호는 필수 값입니다.")
    private String password;

}
