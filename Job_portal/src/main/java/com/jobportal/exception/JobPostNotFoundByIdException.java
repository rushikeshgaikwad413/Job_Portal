package com.jobportal.exception;

public class JobPostNotFoundByIdException extends RuntimeException{

    public JobPostNotFoundByIdException(String message) {
        super(message);
    }
}
