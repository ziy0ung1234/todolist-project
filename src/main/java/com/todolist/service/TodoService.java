package com.todolist.service;

import com.todolist.dto.comment.GetOneCommentResponse;
import com.todolist.dto.todo.*;
import com.todolist.entity.Todo;
import com.todolist.repository.TodoRepository;
import com.todolist.validator.GlobalValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final GlobalValidator globalValidator;

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
    public List<GetAllTodoResponse> findAll(String username) {
        List<Todo> todos;
        if (username != null) {
            todos = todoRepository.findAllByUsernameOrderByCreatedAtDesc(username);
        } else {
            todos = todoRepository.findAllByOrderByCreatedAtDesc();
        }
        return todos.stream()
                .map(todo -> new GetAllTodoResponse(
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
        Todo todo = globalValidator.findTodoOrException(todoId);
        List<GetOneCommentResponse> commentResponses = todo.getComments().stream()
                .map(GetOneCommentResponse::new)
                .toList();
        return new GetOneTodoResponse(
                todo.getId(),
                todo.getUsername(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getCreatedAt(),
                todo.getModifiedAt(),
                commentResponses
        );
    }

    @Transactional
    public UpdateTodoResponse update(Long todoId, UpdateTodoRequest request) {
        Todo todo = globalValidator.findTodoOrException(todoId);
        // 비밀번호 검증
        globalValidator.validatePassword(todo, request.getPassword());
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
    @Transactional
    public void delete(Long todoId, DeleteTodoRequest request) {
        Todo todo = globalValidator.findTodoOrException(todoId);
        globalValidator.validatePassword(todo, request.getPassword());
        todoRepository.deleteById(todoId);
    }
}
