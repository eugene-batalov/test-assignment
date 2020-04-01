package com.example.restservice.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Counter with name specified not found")
public class CounterNotFoundException extends RuntimeException {
}
