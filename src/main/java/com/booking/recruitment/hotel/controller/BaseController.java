package com.booking.recruitment.hotel.controller;

import com.booking.recruitment.hotel.exception.ElementNotFoundException;
import com.booking.recruitment.hotel.exception.ElementWithSameIDAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.reflect.AnnotationFinder;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class BaseController {
    @ExceptionHandler(value = ElementNotFoundException.class)
    public ResponseEntity<String> defaultElementNotFoundHandler(HttpServletRequest httpServletRequest, Exception e) throws Exception {
        log.error("ERROR NOT FOUND");
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = ElementWithSameIDAlreadyExistsException.class)
    public ResponseEntity<String> defaultElementAlreadyExistsHandler(HttpServletRequest httpServletRequest, Exception e) throws Exception {
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        log.error("ERROR CONFLICT");
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
