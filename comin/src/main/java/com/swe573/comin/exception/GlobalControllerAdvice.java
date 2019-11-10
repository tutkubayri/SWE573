package com.swe573.comin.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    private Logger log = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @ExceptionHandler(value = ResourceNotFoundException.class)
    protected ResponseEntity<BaseErrorModel> handleNotFoundException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseErrorModel.builder().message(e.getMessage()).build());
    }

/*    @ExceptionHandler(value = UserAlreadyInUseException.class)
    protected ResponseEntity<BaseErrorModel> handleUsernameAlreadyInUseException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(BaseErrorModel.builder().message(e.getMessage()).build());
    }

    @ExceptionHandler(value = NationalityNumberAlreadyInUseException.class)
    protected ResponseEntity<BaseErrorModel> handleEmployeeNationalityNumberAlreadyInUseException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(BaseErrorModel.builder().message(e.getMessage()).build());
    }*/

    // @Validate For Validating Path Variables and Request Parameters
    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    // error handle for @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();

        //Get all fields errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField() + " -> " + x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);

    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<BaseErrorModel> handleAllExceptions(Exception e) {
        log.error("Error from unknown exception -> {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseErrorModel.builder().message(e.getMessage()).build());
    }
}
