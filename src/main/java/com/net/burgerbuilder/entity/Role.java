package com.net.burgerbuilder.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_gen")
  @SequenceGenerator(name = "role_gen", sequenceName = "role_seq", allocationSize = 1)
  @Column(name = "role_id")
  private Long roleId;

  @Column(name = "role_name")
  private String roleName;

  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
  private List<Customer> customers;
}
