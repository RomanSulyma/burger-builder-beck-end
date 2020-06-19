package com.net.burgerbuilder.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

  private String username;
  private String password;
  private String email;
  private String phone;
}
