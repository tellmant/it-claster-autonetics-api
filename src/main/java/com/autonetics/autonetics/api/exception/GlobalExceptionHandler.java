package com.autonetics.autonetics.api.exception;

import com.autonetics.autonetics.api.model.response.ExceptionDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullEntityReferenceException.class)
    public ResponseEntity<?> handleNullEntityReferenceException(NullEntityReferenceException ex, WebRequest request) {
        log.info("Exception: {}", ex.getMessage());
        ExceptionDto body = getResponseBody(ex, request, 404);
        return super.handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatusCode.valueOf(404), request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        log.info("Exception: {}", ex.getMessage());
        ExceptionDto body = getResponseBody(ex, request, 404);
        return super.handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatusCode.valueOf(404), request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        log.info("Exception: {}", ex.getMessage());
        ExceptionDto body = getResponseBody(ex, request, 403);
        return super.handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatusCode.valueOf(403), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnknownException(Exception ex, WebRequest request) {
        log.info("Exception: {}", ex.getMessage());
        ExceptionDto body = getResponseBody(ex, request, 500);
        return super.handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatusCode.valueOf(500), request);
    }

    private ExceptionDto getResponseBody(Exception ex, WebRequest request, int status) {
        return new ExceptionDto(ex.getMessage(), ((ServletWebRequest) request).getRequest().getRequestURI(), HttpStatus.resolve(status));
    }
}
