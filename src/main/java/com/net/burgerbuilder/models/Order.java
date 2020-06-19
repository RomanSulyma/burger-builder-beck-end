package com.net.burgerbuilder.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Order {

  private Long burgerId;
  private String ingredients;
  private Long totalPrice;
  private Long burgerElementId;
  private String name;
  private String surname;
  private String middleName;
  private String email;
  private String phone;
  private Long age;
}
