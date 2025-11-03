package com.todolist;

import com.todolist.dto.ErrorResponse;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;


@RestControllerAdvice
public class TodoExceptionHandler {

    // IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException e) {
        ErrorResponse response = new ErrorResponse("BAD_REQUEST", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    // PropertyValueException
    @ExceptionHandler(org.hibernate.PropertyValueException.class)
    public ResponseEntity<ErrorResponse> handlePropertyValue(PropertyValueException e) {
        ErrorResponse response = new ErrorResponse("VALIDATION_ERROR",
                e.getPropertyName()+"값이 누락되었습니다.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    //HttpMessageNotReadableException
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        ErrorResponse response = new ErrorResponse("VALIDATION_ERROR",
                "Request Body를 확인해 주세요.");
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    }

