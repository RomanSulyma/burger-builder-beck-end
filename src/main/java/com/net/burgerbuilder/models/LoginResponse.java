package com.net.burgerbuilder.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginResponse {

  private String username;
  private String roles;
  private String token;
  private LocalDateTime expirationTime;
  private String responseMessage;
}
