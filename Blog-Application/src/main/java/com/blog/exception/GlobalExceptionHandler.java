package com.blog.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<ErrorDetails> categoryExceptionHandler(CategoryException ce, WebRequest req){
        
    	ErrorDetails err= new ErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(ce.getMessage());
        err.setDetails(req.getDescription(false));
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(CommentException.class)
    public ResponseEntity<ErrorDetails> commentExceptionHandler(CommentException coe, WebRequest req){
        
    	ErrorDetails err= new ErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(coe.getMessage());
        err.setDetails(req.getDescription(false));
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }
    
    
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> userExceptionHandler(UserException ue, WebRequest req){
        
    	ErrorDetails err= new ErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(ue.getMessage());
        err.setDetails(req.getDescription(false));
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }
    
    
    @ExceptionHandler(PostException.class)
    public ResponseEntity<ErrorDetails> postExceptionHandler(PostException pe, WebRequest req){
        
    	ErrorDetails err= new ErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(pe.getMessage());
        err.setDetails(req.getDescription(false));
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception ce, WebRequest req){
        ErrorDetails err= new ErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(ce.getMessage());
        err.setDetails(req.getDescription(false));
        return new ResponseEntity<ErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
