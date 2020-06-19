package com.net.burgerbuilder.repository;

import com.net.burgerbuilder.entity.Burger;
import com.net.burgerbuilder.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BurgerRepository extends JpaRepository<Burger, Long> {

  Burger findFirstById(Long id);

  Burger findTopByOrderByIdDesc();

  Burger findTopByCustomerOrderByIdDesc(Customer customer);

  List<Burger> findByCustomer(Customer customer);
}
