package app.controllers;


import app.service.exceptions.ErrorDTO;
import app.service.exceptions.UserException;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class UserExceptionHandler {

    @ExceptionHandler( UserException.class )
    public ErrorDTO getUserException(UserException ex ){
        return new ErrorDTO( ex.getCode(), ex.getMessage() );
    }
}
