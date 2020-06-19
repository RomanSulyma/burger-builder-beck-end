package com.net.burgerbuilder.repository;

import com.net.burgerbuilder.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @Query("select c from Customer c where c.name=?1")
  Customer findCustomerByCustomerName(String customerName);
}
