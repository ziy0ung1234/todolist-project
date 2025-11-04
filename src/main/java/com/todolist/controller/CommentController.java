package com.todolist.controller;

import com.todolist.dto.*;
import com.todolist.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/todo-list/{todoId}/comments")
    public ResponseEntity<CreateCommentResponse> createComent (
            @PathVariable Long todoId,
            @RequestBody CreateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(todoId,request));
    }
}
