/**
 * 
 */
package com.synechron.blockchain.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synechron.blockchain.customer.repository.entity.Customer;

/**
 * @author dev
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, String> {
	Customer findByMobile(String mobile);

	Customer findByEmail(String email);
}
