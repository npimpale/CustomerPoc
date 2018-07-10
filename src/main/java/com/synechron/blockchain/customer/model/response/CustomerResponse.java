/**
 * 
 */
package com.synechron.blockchain.customer.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.synechron.blockchain.customer.repository.entity.Customer;

/**
 * @author dev
 *
 */
@JsonInclude(Include.NON_NULL)
public class CustomerResponse extends BaseResponse {

	@JsonProperty("customer")
	private Customer customer;

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerResponse [customer=");
		builder.append(customer);
		builder.append("]");
		return builder.toString();
	}

}
