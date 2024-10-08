package com.jobportal.exception;

public class PlanNotFoundByIdException extends RuntimeException{

    public PlanNotFoundByIdException(String message) {
        super(message);
    }
}
