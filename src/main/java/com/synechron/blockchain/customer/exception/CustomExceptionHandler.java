/**
 * 
 */
package com.synechron.blockchain.customer.exception;

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

import com.synechron.blockchain.customer.model.request.ValidationError;
import com.synechron.blockchain.customer.model.response.BaseResponse;
import com.synechron.blockchain.customer.validator.ValidationErrorBuilder;

/**
 * @author dev
 *
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOGGER.error("Validation Exception Caught.");
		return new ResponseEntity<Object>(createValidationError(exception), HttpStatus.BAD_REQUEST);
	}

	private ValidationError createValidationError(MethodArgumentNotValidException exception) {
		return ValidationErrorBuilder.bindErrors(exception.getBindingResult());
	}

	@ExceptionHandler(value = EntityCreationException.class)
	public ResponseEntity<BaseResponse> handleEntityCreationException(EntityCreationException exception) {
		BaseResponse errorResponse = new BaseResponse();
		errorResponse.setStatus(exception.getStatus());
		errorResponse.setMessage(exception.getMessage());
		ResponseEntity<BaseResponse> responseEntity = new ResponseEntity<BaseResponse>(errorResponse,
				HttpStatus.BAD_REQUEST);

		return responseEntity;
	}

}
