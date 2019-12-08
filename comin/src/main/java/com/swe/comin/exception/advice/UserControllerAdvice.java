package com.swe.comin.exception.advice;

import com.swe.comin.exception.BaseErrorModel;
import com.swe.comin.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class UserControllerAdvice {
	@ExceptionHandler(value = ResourceNotFoundException.class)
	protected ResponseEntity<BaseErrorModel> handleUserNotFoundException(RuntimeException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(BaseErrorModel.builder().message(e.getMessage()).build());
	}

}
