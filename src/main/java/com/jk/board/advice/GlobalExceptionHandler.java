package com.jk.board.advice;

import com.jk.board.exception.ArticleNotFoundException;
import com.jk.board.exception.ArticlePermissionException;
import com.jk.board.exception.UserAlreadyExistsException;
import io.jsonwebtoken.JwtException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(
                        Collectors.toMap(
                                fieldError -> flatFieldName(fieldError.getField()),
                                DefaultMessageSourceResolvable::getDefaultMessage,
                                (existing, replacement) -> existing
                        )
                );
        var preferredOrder = List.of("username", "password");
        var orderedErrors = new LinkedHashMap<String, String>();

        preferredOrder.forEach(field -> {
            if (fieldErrors.containsKey(field)) {
                orderedErrors.put(field, fieldErrors.remove(field));
            }
        });

        orderedErrors.putAll(fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", orderedErrors));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleAuthenticationException(AuthenticationException ex) throws Exception {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "로그인 실패: " + ex.getMessage()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handlerUserAlreadyExistsException(UserAlreadyExistsException ex) throws Exception {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", ex.getMessage()));
    }

    private String flatFieldName(String fieldPath) {
        if (fieldPath.contains(".")) {
            return fieldPath.substring(fieldPath.lastIndexOf('.') + 1);
        }
        return fieldPath;
    }
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Map<String, String>> invalidTokenException (JwtException ex) throws Exception {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", ex.getMessage()));
    }

}
