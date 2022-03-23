package dev.avizuete.springbootms.web;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})
	public ResponseEntity<?> entityNotFoundHandler() {
		return ResponseEntity.notFound().build();
	}
}
