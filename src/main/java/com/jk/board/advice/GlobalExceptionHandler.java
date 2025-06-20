package com.jk.board.advice;

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
}
