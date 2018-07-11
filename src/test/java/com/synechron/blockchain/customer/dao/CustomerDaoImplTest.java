package com.synechron.blockchain.customer.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.synechron.blockchain.customer.exception.EntityCreationException;
import com.synechron.blockchain.customer.exception.EntityNotFoundException;
import com.synechron.blockchain.customer.model.request.CustomerReq;
import com.synechron.blockchain.customer.repository.CustomerRepository;
import com.synechron.blockchain.customer.repository.entity.Customer;
import com.synechron.blockchain.customer.repository.entity.Status;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDaoImplTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerDaoImpl customerDaoImpl;

	private CustomerReq customerReq;

	@Before
	public void setup() {
		customerReq = new CustomerReq("1234567890", "Nikhil", "Pimpale", "n.p@syn.com");
	}

	@Test
	public void testRegisterCustomer() throws Exception {

		Customer customer = new Customer();
		customer.setMobile("1234567890");
		customer.setFirstName("Nikhil");
		customer.setLastName("Pimpale");
		customer.setEmail("n.p@syn.com");
		customer.setStatus(Status.ACTIVE.name());

		Customer customer2 = new Customer();
		customer2.setMobile("1234567890");
		customer2.setFirstName("Nikhil");
		customer2.setLastName("Pimpale");
		customer2.setEmail("n.p@syn.com");
		customer2.setStatus(Status.ACTIVE.name());
		customer2.setId(1);

		when(customerRepository.save(customer)).thenReturn(customer2);
		customerDaoImpl.registerCustomer(customerReq);
		Mockito.verify(customerRepository, times(1)).save(customer);
	}

	@Test(expected = EntityCreationException.class)
	public void testRegisterCustomerException() throws Exception {

		Customer customer = new Customer();
		customer.setMobile("1234567890");
		customer.setFirstName("Nikhil");
		customer.setLastName("Pimpale");
		customer.setEmail("n.p@syn.com");
		customer.setStatus(Status.ACTIVE.name());

		when(customerRepository.save(customer)).thenReturn(null);

		customerDaoImpl.registerCustomer(customerReq);
		Mockito.verify(customerDaoImpl, times(1)).registerCustomer(customerReq);
	}

	@Test
	public void testGetCustomer() {
		Customer customer = new Customer();
		customer.setMobile("1234567890");
		customer.setFirstName("Nikhil");
		customer.setLastName("Pimpale");
		customer.setEmail("n.p@syn.com");
		customer.setStatus(Status.ACTIVE.name());
		customer.setId(1);

		when(customerRepository.findByMobileAndStatus(customerReq.getMobile(), Status.ACTIVE.name()))
				.thenReturn(customer);

		Customer fetchedCustomer = customerDaoImpl.getCustomer(customerReq.getMobile());

		assertNotNull(fetchedCustomer);
		assertNotNull(fetchedCustomer.getId());
		assertTrue("Nikhil".equals(fetchedCustomer.getFirstName()));
	}

	@Test
	public void testUpdateCustomer() {
		Customer dbCustomer = new Customer();
		dbCustomer.setMobile("1234567890");
		dbCustomer.setFirstName("Nikhil");
		dbCustomer.setLastName("Pimpale");
		dbCustomer.setEmail("n.p@syn.com");
		dbCustomer.setStatus(Status.ACTIVE.name());
		dbCustomer.setId(1);

		Customer updatedCustomer = new Customer();
		updatedCustomer.setMobile("1234567890");
		updatedCustomer.setFirstName("Nik");
		updatedCustomer.setLastName("LastName");
		updatedCustomer.setEmail("n.p@syn.com");
		updatedCustomer.setStatus(Status.ACTIVE.name());
		updatedCustomer.setId(1);

		customerReq.setFirstName("Nik");
		customerReq.setLastName("LastName");
		when(customerRepository.findByMobileAndStatus(customerReq.getMobile(), Status.ACTIVE.name()))
				.thenReturn(dbCustomer);

		when(customerRepository.save(dbCustomer)).thenReturn(updatedCustomer);

		Customer fetchedCustomer = customerDaoImpl.updateCustomer(customerReq);

		assertNotNull(fetchedCustomer);
		assertNotNull(fetchedCustomer.getId());
		assertTrue("Nik".equals(fetchedCustomer.getFirstName()));
		assertTrue("LastName".equals(fetchedCustomer.getLastName()));
	}

	@Test
	public void testDeleteCustomer() {
		Customer dbCustomer = new Customer();
		dbCustomer.setMobile("1234567890");
		dbCustomer.setFirstName("Nikhil");
		dbCustomer.setLastName("Pimpale");
		dbCustomer.setEmail("n.p@syn.com");
		dbCustomer.setStatus(Status.ACTIVE.name());
		dbCustomer.setId(1);

		when(customerRepository.findByMobileAndStatus(customerReq.getMobile(), Status.ACTIVE.name()))
				.thenReturn(dbCustomer);

		dbCustomer.setStatus(Status.INACTIVE.name());
		when(customerRepository.save(dbCustomer)).thenReturn(dbCustomer);

		customerDaoImpl.deleteCustomer(customerReq.getMobile());

		verify(customerRepository, times(1)).save(dbCustomer);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteCustomerException() {
		Customer dbCustomer = new Customer();
		dbCustomer.setMobile("1234567890");
		dbCustomer.setFirstName("Nikhil");
		dbCustomer.setLastName("Pimpale");
		dbCustomer.setEmail("n.p@syn.com");
		dbCustomer.setStatus(Status.ACTIVE.name());
		dbCustomer.setId(1);

		when(customerRepository.findByMobileAndStatus(customerReq.getMobile(), Status.ACTIVE.name())).thenReturn(null);

		customerDaoImpl.deleteCustomer(customerReq.getMobile());
	}

	@Test
	public void testUpdateCustomerFirstNameNull() {
		Customer dbCustomer = new Customer();
		dbCustomer.setMobile("1234567890");
		dbCustomer.setFirstName("Nikhil");
		dbCustomer.setLastName("Pimpale");
		dbCustomer.setEmail("n.p@syn.com");
		dbCustomer.setStatus(Status.ACTIVE.name());
		dbCustomer.setId(1);

		Customer updatedCustomer = new Customer();
		updatedCustomer.setMobile("1234567890");
		updatedCustomer.setFirstName("Nikhil");
		updatedCustomer.setLastName("LastName");
		updatedCustomer.setEmail("n.p@syn.com");
		updatedCustomer.setStatus(Status.ACTIVE.name());
		updatedCustomer.setId(1);

		customerReq.setFirstName(null);
		customerReq.setLastName("LastName");
		when(customerRepository.findByMobileAndStatus(customerReq.getMobile(), Status.ACTIVE.name()))
				.thenReturn(dbCustomer);

		when(customerRepository.save(dbCustomer)).thenReturn(updatedCustomer);

		Customer fetchedCustomer = customerDaoImpl.updateCustomer(customerReq);

		assertNotNull(fetchedCustomer);
		assertNotNull(fetchedCustomer.getId());
		assertTrue("Nikhil".equals(fetchedCustomer.getFirstName()));
		assertTrue("LastName".equals(fetchedCustomer.getLastName()));
	}

	@Test
	public void testUpdateCustomerLastNameNull() {
		Customer dbCustomer = new Customer();
		dbCustomer.setMobile("1234567890");
		dbCustomer.setFirstName("Nikhil");
		dbCustomer.setLastName("Pimpale");
		dbCustomer.setEmail("n.p@syn.com");
		dbCustomer.setStatus(Status.ACTIVE.name());
		dbCustomer.setId(1);

		Customer updatedCustomer = new Customer();
		updatedCustomer.setMobile("1234567890");
		updatedCustomer.setFirstName("Nik");
		updatedCustomer.setLastName("Pimpale");
		updatedCustomer.setEmail("n.p@syn.com");
		updatedCustomer.setStatus(Status.ACTIVE.name());
		updatedCustomer.setId(1);

		customerReq.setFirstName("Nik");
		customerReq.setLastName(null);
		when(customerRepository.findByMobileAndStatus(customerReq.getMobile(), Status.ACTIVE.name()))
				.thenReturn(dbCustomer);

		when(customerRepository.save(dbCustomer)).thenReturn(updatedCustomer);

		Customer fetchedCustomer = customerDaoImpl.updateCustomer(customerReq);

		assertNotNull(fetchedCustomer);
		assertNotNull(fetchedCustomer.getId());
		assertTrue("Nik".equals(fetchedCustomer.getFirstName()));
		assertTrue("Pimpale".equals(fetchedCustomer.getLastName()));
	}

	@Test
	public void testUpdateCustomerNull() {
		when(customerRepository.findByMobileAndStatus(customerReq.getMobile(), Status.ACTIVE.name())).thenReturn(null);

		Customer fetchedCustomer = customerDaoImpl.updateCustomer(customerReq);

		assertNull(fetchedCustomer);
	}

	@Test
	public void testIsDuplicateCustomer() {
		Customer customer = new Customer();
		customer.setMobile("1234567890");
		customer.setFirstName("Nikhil");
		customer.setLastName("Pimpale");
		customer.setEmail("n.p@syn.com");
		customer.setStatus(Status.ACTIVE.name());
		customer.setId(1);

		// 1
		when(customerRepository.findByMobileAndStatus(customerReq.getMobile(), Status.ACTIVE.name()))
				.thenReturn(customer);
		boolean isDuplicate = customerDaoImpl.isDuplicateCustomer(customerReq.getMobile());
		assertTrue(isDuplicate);

		// 2
		when(customerRepository.findByMobileAndStatus(customerReq.getMobile(), Status.ACTIVE.name())).thenReturn(null);
		when(customerRepository.findByEmailAndStatus(customerReq.getMobile(), Status.ACTIVE.name()))
				.thenReturn(customer);
		isDuplicate = customerDaoImpl.isDuplicateCustomer(customerReq.getMobile());
		assertTrue(isDuplicate);

		// 3
		when(customerRepository.findByEmailAndStatus(customerReq.getMobile(), Status.ACTIVE.name())).thenReturn(null);
		isDuplicate = customerDaoImpl.isDuplicateCustomer(customerReq.getMobile());
		assertFalse(isDuplicate);

	}
}
