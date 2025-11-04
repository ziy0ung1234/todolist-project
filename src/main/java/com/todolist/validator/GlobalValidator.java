package com.todolist.validator;

import com.todolist.entity.Todo;
import com.todolist.repository.TodoRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GlobalValidator {
    private final TodoRepository todoRepository;

    public GlobalValidator(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo findTodoOrException(Long todoId) {
        return todoRepository.findById(todoId).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
    }
    public void validatePassword(Todo todo, String password) {
        if (!Objects.equals(todo.getPassword(), password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
