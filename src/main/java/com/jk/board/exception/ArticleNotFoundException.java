package com.jk.board.exception;

public class ArticleNotFoundException extends RuntimeException{
    public ArticleNotFoundException(String message) {
        super(message);
    }
}
