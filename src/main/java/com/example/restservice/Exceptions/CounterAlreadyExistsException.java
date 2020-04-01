package com.example.restservice.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Counter with this name already exists")
public class CounterAlreadyExistsException extends RuntimeException {
}
