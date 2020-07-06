package com.net.burgerbuilder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "burger")
public class Burger {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "burger_gen")
  @SequenceGenerator(name = "burger_gen", sequenceName = "burger_seq", allocationSize = 1)
  private Long id;

  @Column
  private String ingredients;

  @Column
  private Long totalPrice;

  @Column(name = "customer_name")
  private String customerName;

  @Column(name = "customer_surname")
  private String customerSurname;

  @Column(name = "customer_middle_name")
  private String customerMiddleName;

  @Column(name = "customer_age")
  private Long customerAge;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;
}
