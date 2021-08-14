package com.thoughtworks.springbootemployee.exception;

public class CompanyNotFound  extends RuntimeException{
    String message;

    public CompanyNotFound(String message) {
        super(message);
    }
}
