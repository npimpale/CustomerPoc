/**
 * 
 */
package com.synechron.blockchain.customer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synechron.blockchain.customer.dao.CustomerDao;
import com.synechron.blockchain.customer.model.request.CustomerReq;
import com.synechron.blockchain.customer.model.response.CustomerResponse;
import com.synechron.blockchain.customer.model.response.ResponseCode;
import com.synechron.blockchain.customer.repository.entity.Customer;

/**
 * @author dev
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDao customerDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.synechron.blockchain.customer.service.CustomerService#registerCustomer(
	 * com.synechron.blockchain.customer.model.request.CustomerReq)
	 */
	@Override
	public CustomerResponse registerCustomer(CustomerReq request) {
		customerDao.registerCustomer(request);
		return prepareCustomerResponse(ResponseCode.SUCCESS, "Customer Registered Successfully.", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.synechron.blockchain.customer.service.CustomerService#getCustomer(java.
	 * lang.String)
	 */
	@Override
	public CustomerResponse getCustomer(String mobile) {
		Customer customer = customerDao.getCustomer(mobile);
		String msg = customer != null ? "Customer Found!" : "Customer Not Found";
		return prepareCustomerResponse(ResponseCode.SUCCESS, msg, customer);
	}

	@Override
	public CustomerResponse updateCustomer(CustomerReq request) {
		Customer updatedCustomer = customerDao.updateCustomer(request);
		String msg = updatedCustomer != null ? "Customer Updated Successfully!" : "Customer Not Found";
		return prepareCustomerResponse(ResponseCode.SUCCESS, msg, updatedCustomer);
	}

	@Override
	public CustomerResponse deleteCustomer(String mobile) {
		customerDao.deleteCustomer(mobile);
		return prepareCustomerResponse(ResponseCode.SUCCESS, "Customer Deleted Successfully!", null);
	}

	private CustomerResponse prepareCustomerResponse(ResponseCode status, String message, Customer customer) {
		CustomerResponse customerResp = new CustomerResponse();
		customerResp.setStatus(status.getStatus());
		customerResp.setMessage(message);
		if (customer != null) {
			customerResp.setMobile(customer.getMobile());
			customerResp.setFirstName(customer.getFirstName());
			customerResp.setLastName(customer.getLastName());
			customerResp.setEmail(customer.getEmail());
		}
		return customerResp;
	}

}
