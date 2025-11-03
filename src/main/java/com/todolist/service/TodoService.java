package com.todolist.service;

import com.todolist.dto.*;
import com.todolist.entity.Todo;
import com.todolist.entity.User;
import com.todolist.repository.TodoRepository;
import com.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateTodoResponse save(CreateTodoRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자 입니다. "));

        Todo todo = new Todo(
                request.getTitle(),
                request.getDescription()
        );
        todo.connectUser(user);
        Todo savedTodo = todoRepository.save(todo);
        return new CreateTodoResponse(
                savedTodo.getId(),
                savedTodo.getUser().getUsername(),
                savedTodo.getTitle(),
                savedTodo.getDescription(),
                savedTodo.getCreatedAt(),
                savedTodo.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetOneTodoResponse> findAll(String username) {
        List<Todo> todos;
        if (username != null) {
            todos = todoRepository.findAllByUser_UsernameOrderByCreatedAtDesc(username);
        } else {
            todos = todoRepository.findAllWithUser();
        }

        return todos.stream()
                .map(todo -> new GetOneTodoResponse(
                        todo.getId(),
                        todo.getUser().getUsername(),
                        todo.getTitle(),
                        todo.getDescription(),
                        todo.getCreatedAt(),
                        todo.getModifiedAt()
                ))
                .toList();
    }
    @Transactional(readOnly = true)
    public GetOneTodoResponse findOne(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글 입니다.")
        );
        return new GetOneTodoResponse(
                todo.getId(),
                todo.getUser().getUsername(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }
}
