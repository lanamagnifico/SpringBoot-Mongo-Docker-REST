package p.vitaly.restexample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import p.vitaly.restexample.dto.ResponseDto;
import p.vitaly.restexample.exception.EntityNotFoundException;

@ControllerAdvice
public final class ExceptionHandler {

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseDto entityNotFoundHandler(EntityNotFoundException e) {
        return new ResponseDto(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseDto resourceNotFoundHandler() {
        return new ResponseDto(HttpStatus.NOT_FOUND, "Resource unavailable");
    }


}
