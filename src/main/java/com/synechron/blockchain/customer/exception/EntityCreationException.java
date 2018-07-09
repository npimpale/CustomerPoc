/**
 * 
 */
package com.synechron.blockchain.customer.exception;

/**
 * @author dev
 *
 */
public class EntityCreationException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public EntityCreationException(String code, String message, String status) {
		super("EntityCreationException", code, message, status);
	}

}
