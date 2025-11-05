package com.todolist.dto.todo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * 기존 할 일(Todo)을 수정하기 위한 요청 DTO
 *
 * <p><b>사용 위치:</b></p>
 * <ul>
 *   <li>{@link com.todolist.controller.TodoController#updateTodo(Long, UpdateTodoRequest)}</li>
 * </ul>
 *
 * <p><b>설명:</b></p>
 * <p>Todo 수정 시 title 또는 username을 선택적으로 변경할 수 있으며,
 * 변경하려면 반드시 해당 Todo 생성 시 사용했던 비밀번호를 함께 전송</p>
 *
 * <p><b>유효성 검증 규칙:</b></p>
 * <ul>
 *   <li><b>title</b> — 선택 입력, 최대 30자</li>
 *   <li><b>username</b> — 선택 입력</li>
 *   <li><b>password</b> — 필수 입력 (비밀번호 일치 시 수정 허용)</li>
 * </ul>
 */
@Getter
@Setter
@NoArgsConstructor
public class UpdateTodoRequest {
    @Size(max=30)
    private String title;
    private String username;
    @NotBlank(message="비밀번호는 필수 값입니다.")
    private String password;
}
