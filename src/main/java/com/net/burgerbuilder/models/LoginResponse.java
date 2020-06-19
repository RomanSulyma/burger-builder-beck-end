package com.net.burgerbuilder.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LoginResponse {

  private String username;
  private String roles;
  private String token;
  private String responseMessage;
}
