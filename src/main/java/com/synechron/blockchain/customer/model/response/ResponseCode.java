/**
 * 
 */
package com.synechron.blockchain.customer.model.response;

/**
 * @author dev
 *
 */
public enum ResponseCode {
	SUCCESS("000", "Success"), FAIL("111", "Fail"), ERROR("222", "Error");

	private String code;
	private String status;

	ResponseCode(String code, String status) {
		this.code = code;
		this.status = status;
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
