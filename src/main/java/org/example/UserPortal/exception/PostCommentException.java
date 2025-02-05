package org.example.UserPortal.exception;

import org.springframework.http.HttpStatus;

public class PostCommentException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public PostCommentException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public PostCommentException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
