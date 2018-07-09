/**
 * 
 */
package com.synechron.blockchain.customer.exception;

/**
 * @author dev
 *
 */
public class EntityNotFoundException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String code, String message, String status) {
		super("EntityBotFoundException", code, message, status);
	}

}
