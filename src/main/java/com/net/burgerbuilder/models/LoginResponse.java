package com.net.burgerbuilder.models;

import lombok.Data;

@Data
public class LoginResponse {

  private String username;
  private String roles;
  private String token;
  private String expirationTime;
  private String responseMessage;
}
