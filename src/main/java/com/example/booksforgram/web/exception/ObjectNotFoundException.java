package com.example.booksforgram.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {
    private final Long objectId;
    public  ObjectNotFoundException(String message, Long objectId) {
        super(message);
        this.objectId = objectId;
    }


    public Long getObjectId() {
        return objectId;
    }
}

