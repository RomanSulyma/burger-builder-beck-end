package com.net.burgerbuilder.service;

import com.net.burgerbuilder.configuration.MyUserDetails;
import com.net.burgerbuilder.entity.Customer;
import com.net.burgerbuilder.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Security UserDetailsService
 */
@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

  private final CustomerRepository customerRepository;

  @Override
  public UserDetails loadUserByUsername(final String customerName) throws UsernameNotFoundException {

    final Customer customer = customerRepository.findCustomerByCustomerName(customerName);

    if (customer == null) {
      throw new UsernameNotFoundException("Could not find user");
    }

    return new MyUserDetails(customer);
  }
}
