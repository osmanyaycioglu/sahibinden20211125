package com.error.rest.error;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorAdvice {

    @Value("${app.domain}")
    private String domain;
    @Value("${app.bounded-context}")
    private String boundedContext;
    @Value("${app.microservice}")
    private String microservice;

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorObj handleException(final IllegalArgumentException exp) {
        return this.generateErrorBase()
                   .setMessage(exp.getMessage())
                   .setCause(1000);
    }

    @ExceptionHandler(MyFeignClientException.class)
    @ResponseStatus(code = HttpStatus.BAD_GATEWAY)
    public ErrorObj handleException(final MyFeignClientException exp) {
        return this.generateErrorBase()
                   .setMessage("Error while calling another MS")
                   .addSubError(exp.getErrorObj())
                   .setCause(8800);
    }

    private ErrorObj generateErrorBase() {
        return new ErrorObj().setDomain(this.domain)
                             .setBoundedContext(this.boundedContext)
                             .setMicroservice(this.microservice);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorObj handleException(final MethodArgumentNotValidException exp) {
        ErrorObj root = this.generateErrorBase()
                            .setMessage("Validation error")
                            .setCause(2000);
        List<ObjectError> allErrorsLoc = exp.getAllErrors();
        for (ObjectError objectErrorLoc : allErrorsLoc) {
            root.addSubError(this.generateErrorBase()
                                 .setMessage(objectErrorLoc.toString())
                                 .setCause(2001));
        }
        return root;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorObj handleException(final ConstraintViolationException exp) {
        ErrorObj root = this.generateErrorBase()
                            .setMessage(exp.getMessage())
                            .setCause(2000);
        Set<ConstraintViolation<?>> allErrorsLoc = exp.getConstraintViolations();
        for (ConstraintViolation<?> objectErrorLoc : allErrorsLoc) {
            root.addSubError(this.generateErrorBase()
                                 .setMessage(objectErrorLoc.toString())
                                 .setCause(2001));
        }
        return root;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorObj handleException(final Exception exp) {
        return this.generateErrorBase()
                   .setMessage(exp.getMessage())
                   .setCause(5000);
    }

}
