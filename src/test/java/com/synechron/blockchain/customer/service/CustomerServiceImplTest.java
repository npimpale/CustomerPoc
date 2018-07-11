package com.synechron.blockchain.customer.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.synechron.blockchain.customer.dao.CustomerDao;
import com.synechron.blockchain.customer.exception.EntityCreationException;
import com.synechron.blockchain.customer.model.request.CustomerReq;
import com.synechron.blockchain.customer.model.response.CustomerResponse;
import com.synechron.blockchain.customer.repository.entity.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;

	@Mock
	private CustomerDao customerDao;

	private CustomerReq customerReq;

	@Before
	public void setup() {
		customerReq = new CustomerReq("1234567890", "Nikhil", "Pimpale", "n.p@syn.com");
	}

	@Test
	public void testRegisterCustomer() {

		when(customerDao.isDuplicateCustomer(customerReq.getMobile())).thenReturn(false);
		when(customerDao.isDuplicateCustomer(customerReq.getEmail())).thenReturn(false);

		CustomerResponse response = customerServiceImpl.registerCustomer(customerReq);
		assertTrue("Success".equals(response.getStatus()));
	}

	@Test(expected = EntityCreationException.class)
	public void testRegisterCustomerDuplicateCustomerMobile() {

		when(customerDao.isDuplicateCustomer(customerReq.getMobile())).thenReturn(true);
		// when(customerDao.isDuplicateCustomer(customerReq.getEmail())).thenReturn(false);

		CustomerResponse response = customerServiceImpl.registerCustomer(customerReq);
		assertTrue("Fail".equals(response.getStatus()));
		verify(customerServiceImpl.registerCustomer(customerReq));
	}

	@Test(expected = EntityCreationException.class)
	public void testRegisterCustomerDuplicateCustomerEmail() {

		when(customerDao.isDuplicateCustomer(customerReq.getEmail())).thenReturn(true);

		CustomerResponse response = customerServiceImpl.registerCustomer(customerReq);
		assertTrue("Fail".equals(response.getStatus()));
		verify(customerServiceImpl.registerCustomer(customerReq));
	}

	@Test
	public void testGetCustomer() {

		Customer customer = new Customer();
		customer.setId(1);
		customer.setMobile("1234567890");
		customer.setFirstName("Nikhil");
		customer.setLastName("Pimpale");
		customer.setEmail("n.p@syn.com");

		when(customerDao.getCustomer(customerReq.getMobile())).thenReturn(customer);

		CustomerResponse response = customerServiceImpl.getCustomer(customerReq.getMobile());
		assertTrue("Success".equals(response.getStatus()));
		assertTrue("Nikhil".equals(customer.getFirstName()));
	}

	@Test
	public void testGetCustomerNotFound() {
		when(customerDao.getCustomer(customerReq.getMobile())).thenReturn(null);

		CustomerResponse response = customerServiceImpl.getCustomer(customerReq.getMobile());
		assertTrue("Success".equals(response.getStatus()));
		assertTrue("Customer Not Found".equals(response.getMessage()));
	}

	@Test
	public void testUpdateCustomer() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setMobile("1234567890");
		customer.setFirstName("Nik");
		customer.setLastName("Pimpale");
		customer.setEmail("n.p@syn.com");

		when(customerDao.updateCustomer(customerReq)).thenReturn(customer);

		CustomerResponse response = customerServiceImpl.updateCustomer(customerReq);
		assertTrue("Success".equals(response.getStatus()));
		assertTrue("Nik".equals(response.getCustomer().getFirstName()));
	}

	@Test
	public void testUpdateCustomerNotFound() {
		when(customerDao.updateCustomer(customerReq)).thenReturn(null);

		CustomerResponse response = customerServiceImpl.updateCustomer(customerReq);
		assertTrue("Success".equals(response.getStatus()));
		assertTrue("Customer Not Found".equals(response.getMessage()));
	}

	@Test
	public void testDeleteCustomer() {
		CustomerResponse response = customerServiceImpl.deleteCustomer(customerReq.getMobile());
		assertTrue("Success".equals(response.getStatus()));
	}

	@Test
	public void testIsDuplicateCustomer() {
		when(customerDao.isDuplicateCustomer(customerReq.getMobile())).thenReturn(true);

		boolean isDuplicate = customerServiceImpl.isDuplicateCustomer(customerReq.getMobile());
		assertTrue(isDuplicate);

	}

	@Test
	public void testIsDuplicateCustomerFalse() {
		when(customerDao.isDuplicateCustomer(customerReq.getMobile())).thenReturn(false);

		boolean isDuplicate = customerServiceImpl.isDuplicateCustomer(customerReq.getMobile());
		assertFalse(isDuplicate);

	}
}
