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
/**
 * Todo(할 일) 관련 비즈니스 로직을 담당하는 서비스 계층클래스
 *
 * <p><b>주요 책임:</b></p>
 * <ul>
 *   <li>할 일 생성, 조회, 수정, 삭제 로직 수행</li>
 *   <li>입력 검증 및 비밀번호 검증 로직 위임</li>
 *   <li>엔티티를 DTO로 변환하여 컨트롤러로 반환</li>
 * </ul>
 *
 * <p><b>트랜잭션 정책:</b></p>
 * <ul>
 *   <li>조회 메서드에는 {@code readOnly = true} 속성을 적용하여 성능 최적화</li>
 *   <li>create/update/delete는 기본 트랜잭션으로 롤백 보장</li>
 * </ul>
 *
 * <p><b>참고:</b></p>
 * <ul>
 *   <li>Todo 존재 여부 검증 및 비밀번호 검증은 {@link GlobalValidator}를 통해 수행</li>
 *   <li>데이터 접근은 {@link TodoRepository}에서 담당</li>
 * </ul>
 */
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
