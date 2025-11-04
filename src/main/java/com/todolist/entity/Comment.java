package com.todolist.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name="comments")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length=50,nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String content;
    @Setter
    @ManyToOne(fetch = FetchType.LAZY) // 기본적인 댓글 조회시 일정 객체 바로 안 불러옴
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo;

    public Comment(String username, String password, String content, Todo todo) {
        this.username = username;
        this.password = password;
        this.content = content;
        this.todo = todo;

        //양방향 관계 설정 추가
        todo.getComments().add(this);
    }
    private void updateContent(String content) {
        this.content = content;
    }
}
