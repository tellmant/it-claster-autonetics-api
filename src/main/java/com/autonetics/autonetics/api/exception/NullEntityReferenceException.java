package com.autonetics.autonetics.api.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NullEntityReferenceException extends RuntimeException {
    public NullEntityReferenceException(String message) {
        super(message);
    }
}
