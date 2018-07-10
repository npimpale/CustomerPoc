/**
 * 
 */
package com.synechron.blockchain.customer.service;

import com.synechron.blockchain.customer.model.request.CustomerReq;
import com.synechron.blockchain.customer.model.response.CustomerResponse;

/**
 * @author dev
 *
 */
public interface CustomerService {

	CustomerResponse registerCustomer(CustomerReq request);

	CustomerResponse getCustomer(String mobile);

	CustomerResponse updateCustomer(CustomerReq request);

	CustomerResponse deleteCustomer(String mobile);

	boolean isDuplicateCustomer(String value);

}
