package com.net.burgerbuilder.configuration;

import com.net.burgerbuilder.entity.Customer;
import com.net.burgerbuilder.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User details implementation, convert Customer entity to UserDetails
 */
public class MyUserDetails implements UserDetails {

  private Customer customer;

  public MyUserDetails(Customer customer) {
    this.customer = customer;
  }

  @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

    final List<Role> roles = customer.getRoles();
    final List<GrantedAuthority> authorities = new ArrayList<>();

    for (final Role role : roles) {
      authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
    }

    return authorities;
  }

  @Override
    public String getPassword() {
    return customer.getPassword();
  }

  @Override
    public String getUsername() {
    return customer.getName();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public Long getCustomerId() {
    return customer.getCustomerId();
  }
}
