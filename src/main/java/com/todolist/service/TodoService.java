package com.todolist.service;

import com.todolist.dto.CreateTodoRequest;
import com.todolist.dto.CreateTodoResponse;
import com.todolist.entity.Todo;
import com.todolist.entity.User;
import com.todolist.repository.TodoRepository;
import com.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
