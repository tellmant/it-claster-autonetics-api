package com.autonetics.autonetics.api.model.response;

import lombok.Value;
import org.springframework.http.HttpStatus;


public record ExceptionDto(String message, String requestURL, HttpStatus status) {
}
