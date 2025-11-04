package com.todolist.controller;

import com.todolist.dto.*;
import com.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/todo-list")
    public ResponseEntity<CreateTodoResponse> createTodo (@RequestBody CreateTodoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.save(request));
    }
    @GetMapping("/todo-list/{todoId}")
    public ResponseEntity<GetOneTodoResponse> getOneTodo (@PathVariable Long todoId) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.findOne(todoId));
    }
    @GetMapping("/todo-list")
    public ResponseEntity<List<GetAllTodoResponse>> getAllTodos(@RequestParam(required=false) String username) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.findAll(username));
    }
    @PatchMapping("/todo-list/{todoId}")
    public ResponseEntity<UpdateTodoResponse> updateTodo(
            @PathVariable Long todoId,
            @RequestBody UpdateTodoRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.update(todoId, request));
    }
    @DeleteMapping("/todo-list/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId, @RequestBody DeleteTodoRequest request) {
        todoService.delete(todoId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
