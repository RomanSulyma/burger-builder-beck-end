package com.net.burgerbuilder.service;

import com.net.burgerbuilder.entity.Burger;
import com.net.burgerbuilder.entity.Customer;
import com.net.burgerbuilder.models.Order;
import com.net.burgerbuilder.repository.BurgerRepository;
import com.net.burgerbuilder.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for search burgers and orders, and add burgers and orders
 */
@Service
@RequiredArgsConstructor
public class BurgerService {

  private final BurgerRepository burgerRepository;
  private final CustomerRepository customerRepository;

    /**
     * Add burger to DB from burger model
     *
     * @param order - Burger model
     * @param customerId - Customer id
     */
  public void addBurger(final Order order, final Long customerId) {

    final Optional<Customer> customerOptional = customerRepository.findById(customerId);

    if (customerOptional.isPresent()) {

      final Burger burger = new Burger();
      final Customer customer = customerOptional.get();

      burger.setIngredients(order.getIngredients());
      burger.setTotalPrice(order.getTotalPrice());
      burger.setBurgerElementId(order.getBurgerElementId());
      burger.setCustomerName(order.getName());
      burger.setCustomerSurname(order.getSurname());
      burger.setCustomerMiddleName(order.getMiddleName());
      burger.setCustomerAge(order.getAge());
      burger.setCustomer(customer);

      burgerRepository.save(burger);
    }
  }

    /**
     * Find all burgers
     *
     * @return - All burgers
     */
  public List<Order> getAllBurgers() {

    final List<Burger> burgers = burgerRepository.findAll();

    return burgers.stream()
                    .map(burger -> {
                      final Customer customer = burger.getCustomer();
                      final Order order = new Order();

                      order.setBurgerId(burger.getId());
                      order.setIngredients(burger.getIngredients());
                      order.setTotalPrice(burger.getTotalPrice());
                      order.setBurgerElementId(burger.getBurgerElementId());
                      order.setName(burger.getCustomerName());
                      order.setSurname(burger.getCustomerSurname());
                      order.setMiddleName(burger.getCustomerMiddleName());
                      order.setEmail(customer.getEmail());
                      order.setPhone(customer.getPhone());
                      order.setAge(burger.getCustomerAge());

                      return order;
                    }).collect(Collectors.toList());
  }

    /**
     * Get single burger by burger id
     *
     * @param id - Burger id
     * @return - Single burger
     */
  public Burger getBurgerById(final Long id) {
    return burgerRepository.findFirstById(id);
  }

    /**
     * Get last created burger
     *
     * @return - Last created burger
     */
  public Burger getLastBurger() {
    return burgerRepository.findTopByOrderByIdDesc();
  }

    /**
     * Return list of burger by current Customer id
     *
     * @param customerId - Customer id
     * @return - List of Order models
     */
  public List<Order> getCustomerBurgers(final Long customerId) {

    final Optional<Customer> customerOptional = customerRepository.findById(customerId);

    if (customerOptional.isPresent()) {

      final List<Burger> burgers = burgerRepository.findByCustomer(customerOptional.get());

      return burgers.stream()
                    .map(burger -> {
                      final Customer customer = burger.getCustomer();
                      final Order order = new Order();

                      order.setBurgerId(burger.getId());
                      order.setIngredients(burger.getIngredients());
                      order.setTotalPrice(burger.getTotalPrice());
                      order.setBurgerElementId(burger.getBurgerElementId());
                      order.setName(burger.getCustomerName());
                      order.setSurname(burger.getCustomerSurname());
                      order.setMiddleName(burger.getCustomerMiddleName());
                      order.setEmail(customer.getEmail());
                      order.setPhone(customer.getPhone());
                      order.setAge(burger.getCustomerAge());

                      return order;
                    }).collect(Collectors.toList());

    } else {
      return new ArrayList<>();
    }
  }

    /**
     * Return last Order by Customer id
     *
     * @param customerId - Customer id
     * @return - Last Order by Customer id
     */
  public Burger getLastCustomerBurger(final Long customerId) {

    final Optional<Customer> customerOptional = customerRepository.findById(customerId);

    return customerOptional.map(burgerRepository::findTopByCustomerOrderByIdDesc).orElse(null);
  }

}
