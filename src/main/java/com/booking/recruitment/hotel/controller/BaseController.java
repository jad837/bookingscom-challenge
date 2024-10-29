package com.booking.recruitment.hotel.controller;

import com.booking.recruitment.hotel.dto.ErrorResponse;
import com.booking.recruitment.hotel.exception.BadRequestException;
import com.booking.recruitment.hotel.exception.ElementNotFoundException;
import com.booking.recruitment.hotel.exception.ElementWithSameIDAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class BaseController {
    @ExceptionHandler(value = ElementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse defaultElementNotFoundHandler(HttpServletRequest httpServletRequest, Exception e) throws Exception {
        log.error("ERROR NOT FOUND");
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(value = ElementWithSameIDAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse defaultElementAlreadyExistsHandler(HttpServletRequest httpServletRequest, Exception e) throws Exception {
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        log.error("ERROR CONFLICT");
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse BadRequestExceptionHandler(HttpServletRequest httpServletRequest, Exception e) throws Exception {
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        log.error("ERROR BAD REQUEST PARAM");
        return new ErrorResponse(e.getMessage());
    }
}
