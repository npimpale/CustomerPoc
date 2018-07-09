/**
 * 
 */
package com.synechron.blockchain.customer.dao;

import com.synechron.blockchain.customer.model.request.CustomerReq;
import com.synechron.blockchain.customer.repository.entity.Customer;

/**
 * @author dev
 *
 */
public interface CustomerDao {

	void registerCustomer(CustomerReq request);

	Customer getCustomer(String mobile);

	Customer updateCustomer(CustomerReq request);

	void deleteCustomer(String mobile);

}
