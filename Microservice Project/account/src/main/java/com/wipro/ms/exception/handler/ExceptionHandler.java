package com.wipro.ms.exception.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wipro.ms.exception.CustomerNotFoundException;

@RestControllerAdvice
public class ExceptionHandler {
	
//	@ExceptionHandler(ProductNotFoundException.class)
//    public final ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
	
	@org.springframework.web.bind.annotation.ExceptionHandler(CustomerNotFoundException.class)
	public final ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex){
		
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationFailureException(MethodArgumentNotValidException ex){
		//List<ObjectError> errorsList = ex.getBindingResult().getAllErrors();
		List<FieldError> errorsList = ex.getBindingResult().getFieldErrors();
		
		Map<String, String> errorMap = new HashMap<>();		
		
		for(FieldError error : errorsList) {
			String fieldName = error.getField();
			String msg = error.getDefaultMessage();
			errorMap.put(fieldName, msg);
		}
		return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
		
//		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
