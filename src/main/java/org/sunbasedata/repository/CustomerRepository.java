package org.sunbasedata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sunbasedata.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
