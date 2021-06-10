package com.serratec.ecommerceapi.controllers.exceptions;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.serratec.ecommerceapi.exceptions.DatabaseException;
import com.serratec.ecommerceapi.exceptions.ResourceNotFoundException;
import com.serratec.ecommerceapi.models.errors.CustomError;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	
	public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustomError err = new CustomError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<CustomError> database(DatabaseException e, HttpServletRequest request) {
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		CustomError err = new CustomError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> argumentNotValidHandler(MethodArgumentNotValidException ex){
		Map<String, String> errosEncontrados = new HashMap<>();
		
		List<ObjectError> erros =ex.getBindingResult().getAllErrors();
	
		for(ObjectError erro : erros) {
			String field = ((FieldError) erro).getField();
			String message = erro.getDefaultMessage();
			errosEncontrados.put(field, message);
		}
		return ResponseEntity.badRequest().body(errosEncontrados);
	
	
	}
	
	
	
    
}
