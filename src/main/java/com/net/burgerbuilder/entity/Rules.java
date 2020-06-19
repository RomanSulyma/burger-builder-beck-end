package com.net.burgerbuilder.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "rules")
public class Rules {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rules_gen")
  @SequenceGenerator(name = "rules_gen", sequenceName = "rules_id_seq", allocationSize = 1)
  private Long id;

  @Column
  private String regex;

  @Column(name = "error_message")
  private String errorMessage;

  @Column
  private String placeholder;

  @Column(name = "field_type")
  private String fieldType;
}
