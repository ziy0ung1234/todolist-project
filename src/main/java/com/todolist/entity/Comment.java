package com.todolist.entity;

import jakarta.persistence.*;
import lombok.*;
/**
 * Comment 엔티티 클래스
 *
 * <p><b>설명:</b></p>
 * <p>각 댓글은 특정 할 일({@link Todo})에 종속되어 있으며,
 * 작성자 이름(username), 비밀번호(password), 댓글 내용(content)을 포함</p>
 *
 * <p>댓글은 {@link Todo}와 <b>다대일(N:1)</b> 연관관계<br>
 * 즉, 하나의 Todo에는 여러 개의 Comment가 연결가능</p>
 *
 * <p><b>참고:</b></p>
 * <ul>
 *   <li><b>fetch = LAZY</b>: 기본 조회 시 Todo 엔티티를 즉시 로딩 하지 않음</li>
 *   <li><b>@JoinColumn(name = "todo_id")</b>: FK로 Todo 엔티티와 연결</li>
 *   <li>생성자에서 <code>todo.getComments().add(this)</code>를 호출하여
 *       양방향 관계의 일관성을 유지</li>
 * </ul>
 */
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
