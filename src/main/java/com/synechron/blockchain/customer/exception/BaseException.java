/**
 * 
 */
package com.synechron.blockchain.customer.exception;

/**
 * @author dev
 *
 */
public class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

	private String message;

	private String status;

	/**
	 * @param message
	 */
	public BaseException(String exception, String code, String message, String status) {
		super(exception);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
