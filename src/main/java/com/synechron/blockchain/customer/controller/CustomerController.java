/**
 * 
 */
package com.synechron.blockchain.customer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
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
	@Timed
	@ExceptionMetered
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CustomerResponse> registerCustomer(
			@Validated(CustomerReq.New.class) @RequestBody CustomerReq request) {
		LOGGER.info("registerCustomer start. CustomerReq : {}", request);
		long startTime = System.currentTimeMillis();

		CustomerResponse response = customerService.registerCustomer(request);

		long endTime = System.currentTimeMillis();
		LOGGER.debug("PERFORMANCE: registerCustomer - Total execution time = {} ms.", (endTime - startTime));

		LOGGER.info("registerCustomer end");
		return new ResponseEntity<CustomerResponse>(response, HttpStatus.CREATED);
	}

	/**
	 * @param mobile
	 * @return
	 */
	@Timed
	@ExceptionMetered
	@RequestMapping(value = "/{mobile}", method = RequestMethod.GET)
	public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("mobile") String mobile) {
		LOGGER.info("getCustomer start. mobile: {}", mobile);
		long startTime = System.currentTimeMillis();

		CustomerResponse response = customerService.getCustomer(mobile);

		long endTime = System.currentTimeMillis();
		LOGGER.debug("PERFORMANCE: getCustomer - Total execution time = {} ms.", (endTime - startTime));

		LOGGER.info("getCustomer end");
		return new ResponseEntity<CustomerResponse>(response, HttpStatus.OK);
	}

	/**
	 * @param request
	 * @return
	 */
	@Timed
	@ExceptionMetered
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<CustomerResponse> updateCustomer(
			@Validated(CustomerReq.Existing.class) @RequestBody CustomerReq request) {
		LOGGER.info("updateCustomer start. mobile: {}", request);
		long startTime = System.currentTimeMillis();

		CustomerResponse response = customerService.updateCustomer(request);

		long endTime = System.currentTimeMillis();
		LOGGER.debug("PERFORMANCE: updateCustomer - Total execution time = {} ms.", (endTime - startTime));

		LOGGER.info("updateCustomer end");
		return new ResponseEntity<CustomerResponse>(response, HttpStatus.OK);
	}

	/**
	 * @param mobile
	 * @return
	 */
	@Timed
	@ExceptionMetered
	@RequestMapping(value = "/{mobile}", method = RequestMethod.DELETE)
	public ResponseEntity<CustomerResponse> deleteCustomer(@PathVariable("mobile") String mobile) {
		LOGGER.info("deleteCustomer start. mobile: {}", mobile);
		long startTime = System.currentTimeMillis();

		CustomerResponse response = customerService.deleteCustomer(mobile);

		long endTime = System.currentTimeMillis();
		LOGGER.debug("PERFORMANCE: deleteCustomer - Total execution time = {} ms.", (endTime - startTime));

		LOGGER.info("deleteCustomer end");
		return new ResponseEntity<CustomerResponse>(response, HttpStatus.OK);
	}

}
