package com.todolist.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * 새로운 댓글을 생성하기 위한 요청 DTO
 *
 * <p><b>사용 위치:</b></p>
 * <ul>
 *   <li>{@link com.todolist.controller.CommentController#createComment(Long, CreateCommentRequest)}</li>
 * </ul>
 *
 * <p><b>유효성 검증 규칙:</b></p>
 * <ul>
 *   <li><b>content</b> — 필수 입력, 최대 100자</li>
 *   <li><b>username</b> — 필수 입력 (댓글 작성자 이름)</li>
 *   <li><b>password</b> — 필수 입력 (댓글 수정/삭제 검증용 비밀번호)</li>
 * </ul>
 *
 * <p>모든 필드는 {@link jakarta.validation.Valid}와 함께 검증되며,
 * 요청 본문(JSON)으로 전달됩니다.</p>
 */
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
