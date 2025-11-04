package com.todolist.dto.comment;

import com.todolist.entity.Comment;
import lombok.Getter;

@Getter
public class GetOneCommentResponse {
    private final Long id;
    private final String username;
    private final String content;
    private final String createdAt;
    private final String modifiedAt;

    public GetOneCommentResponse(Comment comment) {
        this.id = comment.getId();
        this.username = comment.getUsername();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt().toString();
        this.modifiedAt = comment.getModifiedAt().toString();
    }
}
