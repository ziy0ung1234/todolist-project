package com.todolist.controller;

import com.todolist.dto.comment.CreateCommentRequest;
import com.todolist.dto.comment.CreateCommentResponse;
import com.todolist.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo-list")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{todoId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(
            @PathVariable Long todoId,
            @Valid @RequestBody CreateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(todoId,request));
    }
}
