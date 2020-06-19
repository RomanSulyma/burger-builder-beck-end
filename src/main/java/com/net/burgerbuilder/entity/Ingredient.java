package com.net.burgerbuilder.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "ingredient")
public class Ingredient {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_gen")
  @SequenceGenerator(name = "ingredient_gen", sequenceName = "ingredient_seq", allocationSize = 1)
  private String id;

  @Column
  private String name;

  @Column
  private Long price;
}
