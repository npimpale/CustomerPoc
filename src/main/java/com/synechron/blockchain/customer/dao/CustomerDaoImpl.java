/**
 * 
 */
package com.synechron.blockchain.customer.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	@Override
	public void registerCustomer(CustomerReq request) {
		Customer registeredCustomer = customerRepository.save(prepareCustomerEntity(request));
		if (registeredCustomer == null) {
			LOGGER.error("Error while registering customer : {}", request);
			throw new EntityCreationException(ResponseCode.FAIL.getCode(), "Failed to register customer:" + request,
					ResponseCode.FAIL.getStatus());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.synechron.blockchain.customer.dao.CustomerDao#getCustomer(java.lang.
	 * String)
	 */
	@Override
	public Customer getCustomer(String mobile) {
		Customer customer = customerRepository.findByMobile(mobile);
		if (customer == null) {
			LOGGER.info("Customer not found: {}", mobile);
		}
		return customer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.synechron.blockchain.customer.dao.CustomerDao#updateCustomer(com.
	 * synechron.blockchain.customer.model.request.CustomerReq)
	 */
	@Override
	public Customer updateCustomer(CustomerReq request) {
		Customer updatedCustomer = customerRepository.save(prepareCustomerEntity(request));
		return updatedCustomer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.synechron.blockchain.customer.dao.CustomerDao#deleteCustomer(java.lang.
	 * String)
	 */
	@Override
	public void deleteCustomer(String mobile) {
		Customer deletedCustomer = customerRepository.findByMobile(mobile);
		if (deletedCustomer == null) {
			throw new EntityNotFoundException(ResponseCode.FAIL.getCode(), "Customer Not Found!",
					ResponseCode.FAIL.getStatus());
		}
		deletedCustomer.setStatus(Status.INACTIVE.name());
		deletedCustomer = customerRepository.save(deletedCustomer);
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
