package com.todolist.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CreateCommentRequest {
    @NotBlank(message="댓글 내용은 필수 값입니다.")
    @Size(max=100)
    private String content;
    @NotBlank(message="이름은 필수 값입니다.")
    private String username;
    @NotBlank(message="비밀번호는 필수 값입니다.")
    private String password;
}
