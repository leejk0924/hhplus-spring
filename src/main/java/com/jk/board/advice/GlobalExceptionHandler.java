package com.jk.board.advice;

import com.jk.board.exception.ArticleNotFoundException;
import com.jk.board.exception.ArticlePermissionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ArticlePermissionException.class)
    public ResponseEntity<Map<String, String>> handlePermissionException(ArticlePermissionException ex) {
        var error = new HashMap<String, String>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(ArticleNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }
}
