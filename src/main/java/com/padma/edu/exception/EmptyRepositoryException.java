package com.padma.edu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class EmptyRepositoryException extends Exception {

    public EmptyRepositoryException(String str) {
        super(str);
    }
}
