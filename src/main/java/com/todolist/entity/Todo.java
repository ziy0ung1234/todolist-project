package com.todolist.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name="todos")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Todo  extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void connectUser(User user) {
        this.user = user;
        user.getTodos().add(this);
    }
}
