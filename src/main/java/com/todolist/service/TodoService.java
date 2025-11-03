package com.todolist.service;

import com.todolist.dto.*;
import com.todolist.entity.Todo;
import com.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public CreateTodoResponse save(CreateTodoRequest request) {

        Todo todo = new Todo(
                request.getTitle(),
                request.getDescription(),
                request.getUsername(),
                request.getPassword()
        );
        Todo savedTodo = todoRepository.save(todo);
        return new CreateTodoResponse(
                savedTodo.getId(),
                savedTodo.getUsername(),
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
            todos = todoRepository.findAllByUsernameOrderByCreatedAtDesc(username);
        } else {
            todos = todoRepository.findAllByOrderByCreatedAtDesc();
        }

        return todos.stream()
                .map(todo -> new GetOneTodoResponse(
                        todo.getId(),
                        todo.getUsername(),
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
                todo.getUsername(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }

    @Transactional
    public UpdateTodoResponse update(Long todoId, UpdateTodoRequest request) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        // 비밀번호 검증
        if (!todo.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 선택적 수정
        if (request.getTitle() != null && !request.getTitle().isBlank()) {
            todo.updateTitle(request.getTitle());
        }
        if (request.getUsername() != null && !request.getUsername().isBlank()) {
            todo.updateUsername(request.getUsername());
        }
        return new UpdateTodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getUsername(),
                todo.getModifiedAt()
        );
    }

    public void delete(Long todoId, DeleteTodoRequest request) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("존재하지않는 글입니다."));
        if(!Objects.equals(todo.getPassword(), request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        todoRepository.deleteById(todoId);
    }
}
