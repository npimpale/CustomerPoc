/**
 * 
 */
package com.synechron.blockchain.customer.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synechron.blockchain.customer.model.request.CustomerReq;
import com.synechron.blockchain.customer.model.response.CustomerResponse;
import com.synechron.blockchain.customer.service.CustomerService;

/**
 * @author dev
 *
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	/**
	 * @param request
	 * @return {@link ResponseEntity}<{@link CustomerResponse} >
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CustomerResponse> registerCustomer(@Valid @RequestBody CustomerReq request) {
		LOGGER.info("registerCustomer start. CustomerReq : {}", request);
		CustomerResponse response = customerService.registerCustomer(request);
		LOGGER.info("registerCustomer end");
		return new ResponseEntity<CustomerResponse>(response, HttpStatus.CREATED);
	}

	/**
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/{mobile}", method = RequestMethod.GET)
	public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("mobile") String mobile) {
		LOGGER.info("getCustomer start. mobile: {}", mobile);
		CustomerResponse response = customerService.getCustomer(mobile);
		LOGGER.info("getCustomer end");
		return new ResponseEntity<CustomerResponse>(response, HttpStatus.OK);
	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<CustomerResponse> updateCustomer(@Valid @RequestBody CustomerReq request) {
		LOGGER.info("updateCustomer start. mobile: {}", request);
		CustomerResponse response = customerService.updateCustomer(request);
		LOGGER.info("updateCustomer end");
		return new ResponseEntity<CustomerResponse>(response, HttpStatus.OK);
	}

	/**
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/{mobile}", method = RequestMethod.DELETE)
	public ResponseEntity<CustomerResponse> deleteCustomer(@PathVariable("mobile") String mobile) {
		LOGGER.info("deleteCustomer start. mobile: {}", mobile);
		CustomerResponse response = null;
		customerService.deleteCustomer(mobile);
		LOGGER.info("deleteCustomer end");
		return new ResponseEntity<CustomerResponse>(response, HttpStatus.OK);
	}

}
