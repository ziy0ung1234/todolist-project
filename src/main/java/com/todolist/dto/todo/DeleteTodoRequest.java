package com.todolist.dto.todo;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
/**
 * 기존 할 일(Todo)을 삭제하기 위한 요청 DTO
 *
 * <p><b>사용 위치:</b></p>
 * <ul>
 *   <li>{@link com.todolist.controller.TodoController#deleteTodo(Long, DeleteTodoRequest)}</li>
 * </ul>
 *
 * <p><b>설명:</b></p>
 * <p>Todo 삭제 시 클라이언트는 해당 Todo 작성 시 사용했던 비밀번호를 함께 전송해야 함
 * 서버에서는 이 비밀번호를 검증하여 일치할 경우에만 삭제를 수행</p>
 *
 * <p><b>유효성 검증 규칙:</b></p>
 * <ul>
 *   <li><b>password</b> — 필수 입력</li>
 * </ul>
 */
@Getter
public class DeleteTodoRequest {
    @NotBlank(message="비밀번호는 필수 값 입니다.")
    private String password;
}
