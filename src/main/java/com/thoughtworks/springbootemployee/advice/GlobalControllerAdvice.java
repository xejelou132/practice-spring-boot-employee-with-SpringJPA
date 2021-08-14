package com.thoughtworks.springbootemployee.advice;

import com.thoughtworks.springbootemployee.exception.CompanyNotFound;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorResponse employeeNotFoundExceptionHandling(EmployeeNotFound employeeNotFound) {
        return new ErrorResponse(employeeNotFound.getMessage(), HttpStatus.NOT_FOUND.name());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorResponse CompanyNotFoundExceptionHandling(CompanyNotFound companyNotFound) {
        return new ErrorResponse(companyNotFound.getMessage(), HttpStatus.NOT_FOUND.name());
    }
}
