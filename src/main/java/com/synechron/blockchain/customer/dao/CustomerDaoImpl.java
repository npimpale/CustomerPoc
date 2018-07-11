/**
 * 
 */
package com.synechron.blockchain.customer.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.synechron.blockchain.customer.exception.EntityCreationException;
import com.synechron.blockchain.customer.exception.EntityNotFoundException;
import com.synechron.blockchain.customer.model.request.CustomerReq;
import com.synechron.blockchain.customer.model.response.ResponseCode;
import com.synechron.blockchain.customer.repository.CustomerRepository;
import com.synechron.blockchain.customer.repository.entity.Customer;
import com.synechron.blockchain.customer.repository.entity.Status;

/**
 * @author dev
 *
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDaoImpl.class);

	@Autowired
	private CustomerRepository customerRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.synechron.blockchain.customer.dao.CustomerDao#registerCustomer(com.
	 * synechron.blockchain.customer.model.request.CustomerReq)
	 */
	@Timed
	@ExceptionMetered
	@Override
	public void registerCustomer(CustomerReq request) {
		LOGGER.info("registerCustomer start");
		long startTime = System.currentTimeMillis();
		Customer customer = prepareCustomerEntity(request);
		Customer registeredCustomer = customerRepository.save(customer);
		if (registeredCustomer == null) {
			LOGGER.error("Error while registering customer : {}", request);
			throw new EntityCreationException(ResponseCode.FAIL.getCode(), "Failed to register customer:" + request,
					ResponseCode.FAIL.getStatus());
		}
		long endTime = System.currentTimeMillis();
		LOGGER.debug("PERFORMANCE: registerCustomer - Total execution time = {} ms.", (endTime - startTime));

		LOGGER.info("registerCustomer end");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.synechron.blockchain.customer.dao.CustomerDao#getCustomer(java.lang.
	 * String)
	 */
	@Timed
	@ExceptionMetered
	@Override
	public Customer getCustomer(String mobile) {
		LOGGER.info("getCustomer start");
		long startTime = System.currentTimeMillis();

		Customer customer = customerRepository.findByMobileAndStatus(mobile, Status.ACTIVE.name());

		long endTime = System.currentTimeMillis();
		LOGGER.debug("PERFORMANCE: getCustomer - Total execution time = {} ms.", (endTime - startTime));

		LOGGER.info("getCustomer end");

		return customer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.synechron.blockchain.customer.dao.CustomerDao#updateCustomer(com.
	 * synechron.blockchain.customer.model.request.CustomerReq)
	 */
	@Timed
	@ExceptionMetered
	@Override
	public Customer updateCustomer(CustomerReq request) {
		LOGGER.info("updateCustomer start");
		long startTime = System.currentTimeMillis();

		Customer dbCustomer = customerRepository.findByMobileAndStatus(request.getMobile(), Status.ACTIVE.name());
		Customer updatedCustomer = null;
		if (dbCustomer != null) {
			dbCustomer.setFirstName(
					!StringUtils.isEmpty(request.getFirstName()) ? request.getFirstName() : dbCustomer.getFirstName());
			dbCustomer.setLastName(
					!StringUtils.isEmpty(request.getLastName()) ? request.getLastName() : dbCustomer.getLastName());
			updatedCustomer = customerRepository.save(dbCustomer);
		}

		long endTime = System.currentTimeMillis();
		LOGGER.debug("PERFORMANCE: updateCustomer - Total execution time = {} ms.", (endTime - startTime));

		LOGGER.info("updateCustomer end");

		return updatedCustomer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.synechron.blockchain.customer.dao.CustomerDao#deleteCustomer(java.lang.
	 * String)
	 */
	@Timed
	@ExceptionMetered
	@Override
	public void deleteCustomer(String mobile) {
		LOGGER.info("deleteCustomer start");
		long startTime = System.currentTimeMillis();

		Customer deletedCustomer = customerRepository.findByMobileAndStatus(mobile, Status.ACTIVE.name());
		if (deletedCustomer == null) {
			throw new EntityNotFoundException(ResponseCode.FAIL.getCode(), "Customer Not Found!",
					ResponseCode.FAIL.getStatus());
		}
		deletedCustomer.setStatus(Status.INACTIVE.name());
		deletedCustomer = customerRepository.save(deletedCustomer);

		long endTime = System.currentTimeMillis();
		LOGGER.debug("PERFORMANCE: deleteCustomer - Total execution time = {} ms.", (endTime - startTime));

		LOGGER.info("deleteCustomer end");
	}

	@Timed
	@ExceptionMetered
	@Override
	public boolean isDuplicateCustomer(String value) {
		LOGGER.info("isDuplicateCustomer start");
		long startTime = System.currentTimeMillis();
		boolean isDuplicate = customerRepository.findByMobileAndStatus(value, Status.ACTIVE.name()) == null
				? customerRepository.findByEmailAndStatus(value, Status.ACTIVE.name()) == null ? false : true
				: true;

		long endTime = System.currentTimeMillis();
		LOGGER.debug("PERFORMANCE: isDuplicateCustomer - Total execution time = {} ms.", (endTime - startTime));

		LOGGER.info("isDuplicateCustomer end");
		return isDuplicate;
	}

	private Customer prepareCustomerEntity(CustomerReq request) {
		Customer customer = new Customer();
		customer.setMobile(request.getMobile());
		customer.setFirstName(request.getFirstName());
		customer.setLastName(request.getLastName());
		customer.setEmail(request.getEmail());
		return customer;
	}

}
