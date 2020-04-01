package com.example.restservice.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Counter name should be specified")
public class EmptyNameException extends RuntimeException {
}
