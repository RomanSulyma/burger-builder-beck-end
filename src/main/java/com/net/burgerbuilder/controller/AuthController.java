package com.net.burgerbuilder.controller;

import com.net.burgerbuilder.entity.Customer;
import com.net.burgerbuilder.entity.Role;
import com.net.burgerbuilder.models.LoginRequest;
import com.net.burgerbuilder.models.LoginResponse;
import com.net.burgerbuilder.models.SignUpRequest;
import com.net.burgerbuilder.repository.CustomerRepository;
import com.net.burgerbuilder.repository.RoleRepository;
import com.net.burgerbuilder.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Authentication controllers to signUp and signIn
 */
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;
  private final CustomerRepository customerRepository;
  private final RoleRepository roleRepository;
  private final Environment environment;
  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @PostMapping("/signin")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody final LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    final String jwt = jwtUtils.generateJwtToken(authentication);
    final Long expirationTime = Long.valueOf(Objects.requireNonNull(environment.getProperty("token.expiration.time")));

    final LoginResponse loginResponse = new LoginResponse();
    loginResponse.setUsername(authentication.getName());
    loginResponse.setRoles(authentication.getAuthorities().toString());
    loginResponse.setToken(jwt);
    loginResponse.setExpirationTime(LocalDateTime.now().plusSeconds(expirationTime / 1000));
    loginResponse.setResponseMessage("Login successful");

    return new ResponseEntity<>(loginResponse, HttpStatus.OK);
  }

  @PostMapping("/signup")
    public ResponseEntity<LoginResponse> registerUser(@RequestBody final SignUpRequest signUpRequest) {

    final LoginResponse loginResponse = new LoginResponse();

    if (customerRepository.findCustomerByCustomerName(signUpRequest.getUsername()) != null) {
      loginResponse.setResponseMessage("User already exists");
      return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
    }

    if (StringUtils.isEmpty(signUpRequest.getUsername()) || StringUtils.isEmpty(signUpRequest.getPassword())) {
      loginResponse.setResponseMessage("Bad credentials");
      return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
    }

    final Customer customer = new Customer();
    final Role customerRole = roleRepository.findRoleByRoleName("ROLE_USER");
    final List<Role> customerRoles = new ArrayList<>();
    customerRoles.add(customerRole);

    // Create new user's account
    customer.setName(signUpRequest.getUsername());
    customer.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    customer.setEmail(signUpRequest.getEmail());
    customer.setPhone(signUpRequest.getPhone());
    customer.setRoles(customerRoles);

    customerRepository.save(customer);

    final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpRequest.getUsername(), signUpRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    final String jwt = jwtUtils.generateJwtToken(authentication);
    final Long expirationTime = Long.valueOf(Objects.requireNonNull(environment.getProperty("token.expiration.time")));

    loginResponse.setResponseMessage("Login successful");
    loginResponse.setUsername(authentication.getName());
    loginResponse.setRoles(authentication.getAuthorities().toString());
    loginResponse.setExpirationTime(LocalDateTime.now().plusSeconds(expirationTime / 1000));
    loginResponse.setToken(jwt);

    return new ResponseEntity<>(loginResponse, HttpStatus.OK);
  }
}
