package com.todolist.controller;

import com.todolist.dto.CreateTodoRequest;
import com.todolist.dto.CreateTodoResponse;
import com.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/todo")
    public ResponseEntity<CreateTodoResponse> createTodo (@RequestBody CreateTodoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.save(request));
    }
}
