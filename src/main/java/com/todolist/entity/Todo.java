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
    @Column(length=50,nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String title;
    private String description;


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
