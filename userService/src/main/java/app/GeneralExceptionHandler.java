package app;

import app.service.exceptions.ErrorDTO;
import app.service.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice( basePackages = "com.example.web.rest" )
public class GeneralExceptionHandler {

    @ExceptionHandler( NotFoundException.class )
    public ErrorDTO getException(NotFoundException ex ){
        return new ErrorDTO( ex.getCode(), ex.getMessage() );
    }
}
