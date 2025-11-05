package com.todolist.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Todo 엔티티 클래스
 *
 * <p><b>설명:</b></p>
 * <p>사용자가 작성한 할 일(Todo)을 표현하는 엔티티로,
 * 제목(title), 내용(description), 작성자(username), 비밀번호(password)를 포함</p>
 *
 * <p>각 Todo는 여러 개의 {@link Comment}를 가질 수 있으며,
 * {@code @OneToMany(mappedBy = "todo")}를 통해 댓글과 양방향 연관관계 설정</p>
 *
 * <p><b>연관관계 설정:</b></p>
 * <ul>
 *   <li><b>mappedBy = "todo"</b> — Comment 엔티티의 <code>todo</code> 필드에 의해 매핑</li>
 *   <li><b>cascade = CascadeType.ALL</b> — Todo가 저장/삭제될 때 관련 댓글도 함께 영속성 전이</li>
 *   <li><b>orphanRemoval = true</b> — Todo에서 댓글이 제거되면 DB에서도 자동 삭제</li>
 * </ul>
 *
 * <p><b>참고:</b></p>
 * <ul>
 *   <li>비밀번호(password)는 수정 및 삭제 시 검증용으로 사용</li>
 *   <li>댓글(Comment)은 Todo 삭제 시 함께 제거</li>
 * </ul>
 */
@Getter
@Entity
@Table(name="todos")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Todo  extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(length=50,nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String title;
    private String description;
    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    public Todo(String title, String description, String username, String password) {
        this.title = title;
        this.description = description;
        this.username = username;
        this.password = password;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateUsername(String username) {
        this.username = username;
    }
    public void updatePassword(String password) {
        this.password = password;
    }
}
