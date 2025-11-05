package com.todolist.controller;

import com.todolist.dto.comment.CreateCommentRequest;
import com.todolist.dto.comment.CreateCommentResponse;
import com.todolist.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 댓글(Comment) 관련 요청을 처리하는 REST API 컨트롤러
 *
 * <p><b>기능 요약:</b></p>
 * <ul>
 *   <li>특정 Todo에 새로운 댓글을 생성하는 엔드포인트 제공</li>
 *   <li>입력 데이터 검증(Validation) 및 서비스 계층 위임</li>
 *   <li>생성 성공 시 HTTP 201(CREATED) 상태 코드 반환</li>
 * </ul>
 *
 * <p><b>엔드포인트:</b></p>
 * <ul>
 *   <li>POST /todos/{todoId}/comments — 댓글 작성</li>
 * </ul>
 *
 * <p><b>참고:</b></p>
 * <ul>
 *   <li>비즈니스 로직은 {@link CommentService}에서 처리</li>
 *   <li>입력 검증은 {@link jakarta.validation.Valid}를 통해 수행</li>
 * </ul>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{todoId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(
            @PathVariable Long todoId,
            @Valid @RequestBody CreateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(todoId,request));
    }
}
