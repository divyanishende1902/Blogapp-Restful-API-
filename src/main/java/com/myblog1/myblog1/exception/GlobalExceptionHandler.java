package com.myblog1.myblog1.exception;

import com.myblog1.myblog1.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.jws.WebService;
import java.util.Date;

// springboot will get clear idea wherever get exception springboot passed the exception t @ControllerAdvice class
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    //Specific Exception
    @ExceptionHandler (ResourceNotFoundException.class)
public ResponseEntity<ErrorDetails >handleResourceNotFoundException(
            ResourceNotFoundException exception,//this line behave like a catch block
            WebRequest webRequest//this line give us uri details.
    ){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
                return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND );


}
    @ExceptionHandler (Exception.class)
    public ResponseEntity<ErrorDetails >handleAllException(
            Exception exception,//this line behave like a catch block
            WebRequest webRequest//this line give us uri details.
    ){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR );


    }
}
