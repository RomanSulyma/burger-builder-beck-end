package com.net.burgerbuilder.controller;

import com.net.burgerbuilder.configuration.MyUserDetails;
import com.net.burgerbuilder.entity.Burger;
import com.net.burgerbuilder.models.Order;
import com.net.burgerbuilder.service.BurgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controllers to work with current burger and user orders
 */
@RequiredArgsConstructor
@RestController
public class BurgerController {

  private final BurgerService burgerService;

  /**
   * Get all burgers
   *
   * @return - List of orders(burgers)
   */
  @RequestMapping(method = RequestMethod.GET, value = "/burger")
    public ResponseEntity<List<Order>> getAllBurgers() {
    return new ResponseEntity<>(burgerService.getAllBurgers(), HttpStatus.OK);
  }

  /**
   * Return single burger by burger id
   *
   * @param id - Burger id
   * @return - Single burger by id
   */
  @RequestMapping(method = RequestMethod.GET, value = "/burger/{id}")
    public ResponseEntity<Burger> getBurgerById(@PathVariable final Long id) {
    return new ResponseEntity<>(burgerService.getBurgerById(id), HttpStatus.OK);
  }

  /**
   *  Creates order in DB from order model
   *
   * @param order - Order model
   * @return - Burger entity
   */
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @RequestMapping(method = RequestMethod.POST, value = "/burger")
    public ResponseEntity<Burger> addBurger(@RequestBody final Order order) {

    final MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    final Long customerId = userDetails.getCustomerId();

    burgerService.addBurger(order, customerId);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }

  /**
   * Get last created burger
   *
   * @return - Burger entity
   */
  @RequestMapping(method = RequestMethod.GET, value = "/burger/last")
    public ResponseEntity<Burger> getLastBurger() {
    return new ResponseEntity<>(burgerService.getLastBurger(), HttpStatus.OK);
  }

  /**
   * Return burger by current Customer id
   *
   * @return - Burger by current Customer id
   */
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @RequestMapping(method = RequestMethod.GET, value = "/burger/customer")
    public ResponseEntity<List<Order>> getCustomerBurgers() {

    final MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    final Long customerId = userDetails.getCustomerId();

    return new ResponseEntity<>(burgerService.getCustomerBurgers(customerId), HttpStatus.OK);
  }

  /**
   * Get last created burger by current customer id
   *
   * @return - Last created burger by current customer id
   */
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @RequestMapping(method = RequestMethod.GET, value = "/burger/customer/last")
    public ResponseEntity<Burger> getLastCustomerBurger() {

    final MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    final Long customerId = userDetails.getCustomerId();

    return new ResponseEntity<>(burgerService.getLastCustomerBurger(customerId), HttpStatus.OK);
  }
}
