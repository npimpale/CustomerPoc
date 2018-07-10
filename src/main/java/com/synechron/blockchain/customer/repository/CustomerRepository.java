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
	Customer findByMobileAndStatus(String mobile, String status);

	Customer findByEmailAndStatus(String email, String status);
}
