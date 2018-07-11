/**
 * 
 */
package com.synechron.blockchain.customer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.synechron.blockchain.customer.dao.CustomerDao;
import com.synechron.blockchain.customer.exception.EntityCreationException;
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
	@Timed
	@ExceptionMetered
	@Override
	public CustomerResponse registerCustomer(CustomerReq request) {
		LOGGER.info("registerCustomer start");
		long startTime = System.currentTimeMillis();

		if (customerDao.isDuplicateCustomer(request.getMobile())
				|| customerDao.isDuplicateCustomer(request.getEmail())) {
			throw new EntityCreationException(ResponseCode.FAIL.getCode(), "Customer Already Exists!",
					ResponseCode.FAIL.getStatus());
		}
		customerDao.registerCustomer(request);

		long endTime = System.currentTimeMillis();
		LOGGER.debug("PERFORMANCE: registerCustomer - Total execution time = {} ms.", (endTime - startTime));

		LOGGER.info("registerCustomer end");
		return prepareCustomerResponse(ResponseCode.SUCCESS, "Customer Registered Successfully.", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.synechron.blockchain.customer.service.CustomerService#getCustomer(java.
	 * lang.String)
	 */
	@Timed
	@ExceptionMetered
	@Override
	public CustomerResponse getCustomer(String mobile) {
		LOGGER.info("getCustomer start");
		long startTime = System.currentTimeMillis();

		Customer customer = customerDao.getCustomer(mobile);
		String msg = customer != null ? "Customer Found!" : "Customer Not Found";

		long endTime = System.currentTimeMillis();
		LOGGER.debug("PERFORMANCE: getCustomer - Total execution time = {} ms.", (endTime - startTime));

		LOGGER.info("getCustomer end");
		return prepareCustomerResponse(ResponseCode.SUCCESS, msg, customer);
	}

	@Timed
	@ExceptionMetered
	@Override
	public CustomerResponse updateCustomer(CustomerReq request) {
		LOGGER.info("updateCustomer start");
		long startTime = System.currentTimeMillis();

		Customer updatedCustomer = customerDao.updateCustomer(request);
		String msg = updatedCustomer != null ? "Customer Updated Successfully!" : "Customer Not Found";

		long endTime = System.currentTimeMillis();
		LOGGER.debug("PERFORMANCE: updateCustomer - Total execution time = {} ms.", (endTime - startTime));

		LOGGER.info("updateCustomer end");
		return prepareCustomerResponse(ResponseCode.SUCCESS, msg, updatedCustomer);
	}

	@Timed
	@ExceptionMetered
	@Override
	public CustomerResponse deleteCustomer(String mobile) {
		LOGGER.info("deleteCustomer start");
		long startTime = System.currentTimeMillis();

		customerDao.deleteCustomer(mobile);

		long endTime = System.currentTimeMillis();
		LOGGER.debug("PERFORMANCE: deleteCustomer - Total execution time = {} ms.", (endTime - startTime));

		LOGGER.info("deleteCustomer end");
		return prepareCustomerResponse(ResponseCode.SUCCESS, "Customer Deleted Successfully!", null);
	}

	@Timed
	@ExceptionMetered
	@Override
	public boolean isDuplicateCustomer(String request) {
		LOGGER.info("isDuplicateCustomer start");
		long startTime = System.currentTimeMillis();
		boolean isDuplicate = customerDao.isDuplicateCustomer(request);

		long endTime = System.currentTimeMillis();
		LOGGER.debug("PERFORMANCE: isDuplicateCustomer - Total execution time = {} ms.", (endTime - startTime));

		LOGGER.info("isDuplicateCustomer end");
		return isDuplicate;
	}

	private CustomerResponse prepareCustomerResponse(ResponseCode status, String message, Customer customer) {
		CustomerResponse customerResp = new CustomerResponse();
		customerResp.setStatus(status.getStatus());
		customerResp.setMessage(message);
		if (customer != null) {
			customerResp.setCustomer(customer);
		}
		return customerResp;
	}

}
