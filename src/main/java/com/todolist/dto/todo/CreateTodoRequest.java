package com.todolist.dto.todo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * 새로운 할 일(Todo)을 생성하기 위한 요청 DTO
 *
 * <p><b>사용 위치:</b></p>
 * <ul>
 *   <li>{@link com.todolist.controller.TodoController#createTodo(CreateTodoRequest)}</li>
 * </ul>
 *
 * <p><b>유효성 검증 규칙:</b></p>
 * <ul>
 *   <li><b>title</b> — 필수 입력, 최대 30자</li>
 *   <li><b>description</b> — 필수 입력, 최대 200자</li>
 *   <li><b>username</b> — 필수 입력 (작성자 이름)</li>
 *   <li><b>password</b> — 필수 입력 (수정/삭제 검증용 비밀번호)</li>
 * </ul>

 * <p>모든 필드는 {@link jakarta.validation.Valid}를 통해 컨트롤러에서 자동 검증됩니다.</p>
 */
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
