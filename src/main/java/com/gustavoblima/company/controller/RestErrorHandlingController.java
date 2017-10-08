package com.gustavoblima.company.controller;

import com.gustavoblima.company.dto.ApiErrorDTO;
import com.gustavoblima.company.exception.CompanyNotFoundException;
import com.gustavoblima.company.exception.IndustryNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestErrorHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IndustryNotFoundException.class)
    protected ResponseEntity<ApiErrorDTO> industryNotFound( IndustryNotFoundException e) {
        return new ResponseEntity<ApiErrorDTO>(new ApiErrorDTO(400, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ApiErrorDTO> entityNotFound( EntityNotFoundException e) {
        return new ResponseEntity<ApiErrorDTO>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    protected ResponseEntity<ApiErrorDTO> companyNotfound( CompanyNotFoundException e) {
        return new ResponseEntity<ApiErrorDTO>(new ApiErrorDTO(400, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>(new ApiErrorDTO(400, "Bad Request"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ApiErrorDTO> handleConstraintViolation(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        String messages = new String();
        constraintViolations.forEach(constraintViolation -> messages.concat(String.format("%s value '%s' %s ", constraintViolation.getPropertyPath(),
                        constraintViolation.getInvalidValue(), constraintViolation.getMessage())));
        return new ResponseEntity<ApiErrorDTO>(new ApiErrorDTO(409, messages), HttpStatus.CONFLICT);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<ApiErrorDTO> handleDataViolation(DataIntegrityViolationException e) {
        String message = e.getRootCause().getMessage();
        return new ResponseEntity<ApiErrorDTO>(new ApiErrorDTO(409, "Duplicated data violation"), HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>(new ApiErrorDTO(500), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
