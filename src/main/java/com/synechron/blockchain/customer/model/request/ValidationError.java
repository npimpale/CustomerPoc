/**
 * 
 */
package com.synechron.blockchain.customer.model.request;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author dev
 *
 */
@JsonPropertyOrder({ "errorMessage", "errors" })
public class ValidationError {

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonProperty("errors")
	private List<String> errors = new ArrayList<>();

	@JsonProperty("errorMessage")
	private final String errorMessage;

	/**
	 * 
	 */
	public ValidationError(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errors
	 */
	public List<String> getErrors() {
		return errors;
	}

	/**
	 * @param errors
	 *            the errors to set
	 */
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	public void addValidationError(String error) {
		this.errors.add(error);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ValidationError [errors=");
		builder.append(errors);
		builder.append(", errorMessage=");
		builder.append(errorMessage);
		builder.append("]");
		return builder.toString();
	}

}
