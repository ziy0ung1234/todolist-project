package com.todolist.controller;

import com.todolist.dto.todo.*;
import com.todolist.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Todo(할 일) 관련 REST API 요청을 처리하는 컨트롤러
 *
 * <p><b>기능 요약:</b></p>
 * <ul>
 *   <li>할 일 생성, 조회, 수정, 삭제 CRUD 엔드포인트 제공</li>
 *   <li>요청 데이터 유효성 검증 및 서비스 계층 위임</li>
 *   <li>응답 상태 코드에 따라 HTTP 응답을 반환</li>
 * </ul>
 *
 * <p><b>엔드포인트:</b></p>
 * <ul>
 *   <li>POST /todos — 새로운 Todo 생성</li>
 *   <li>GET /todos — 전체 Todo 목록 조회 (username 필터 가능)</li>
 *   <li>GET /todos/{todoId} — 단일 Todo 상세 조회</li>
 *   <li>PATCH /todos/{todoId} — Todo 수정</li>
 *   <li>DELETE /todos/{todoId} — Todo 삭제</li>
 * </ul>
 *
 * <p><b>참고:</b></p>
 * <ul>
 *   <li>비즈니스 로직은 {@link TodoService}에서 처리</li>
 *   <li>요청 DTO는 {@code com.todolist.dto.todo} 패키지에 정의</li>
 *   <li>입력 검증은 {@link jakarta.validation.Valid} 어노테이션으로 수행</li>
 * </ul>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<CreateTodoResponse> createTodo (@Valid @RequestBody CreateTodoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.save(request));
    }
    @GetMapping("/{todoId}")
    public ResponseEntity<GetOneTodoResponse> getOneTodo (@PathVariable Long todoId) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.findOne(todoId));
    }
    @GetMapping
    public ResponseEntity<List<GetAllTodoResponse>> getAllTodos(@RequestParam(required=false) String username) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.findAll(username));
    }
    @PatchMapping("/{todoId}")
    public ResponseEntity<UpdateTodoResponse> updateTodo(
            @PathVariable Long todoId,
            @RequestBody UpdateTodoRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.update(todoId, request));
    }
    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId, @Valid @RequestBody DeleteTodoRequest request) {
        todoService.delete(todoId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
