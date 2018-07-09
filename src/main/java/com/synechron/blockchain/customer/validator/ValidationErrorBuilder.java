/**
 * 
 */
package com.synechron.blockchain.customer.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.synechron.blockchain.customer.model.request.ValidationError;

/**
 * @author dev
 *
 */
public class ValidationErrorBuilder {

	public static ValidationError bindErrors(Errors errors) {
		ValidationError error = new ValidationError("Validation failed !. " + errors.getErrorCount() + " error(s)");
		for (ObjectError e : errors.getAllErrors()) {
			error.addValidationError(e.getDefaultMessage());
		}
		return error;
	}

}
