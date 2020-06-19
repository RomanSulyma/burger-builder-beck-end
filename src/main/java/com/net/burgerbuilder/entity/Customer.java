package com.net.burgerbuilder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_gen")
  @SequenceGenerator(name = "customer_gen", sequenceName = "customer_seq", allocationSize = 1)
  @Column(name = "customer_id")
  private Long customerId;

  @Column(name = "name")
  private String name;

  @Column(name = "password")
  private String password;

  @Column
  private String email;

  @Column
  private String phone;

  @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
  @JoinTable(
          name = "customer_role",
          joinColumns = { @JoinColumn(name = "customer_id") },
          inverseJoinColumns = { @JoinColumn(name = "role_id") }
  )
  private List<Role> roles;

  @JsonIgnore
  @OneToMany(mappedBy = "customer")
  private List<Burger> burgers;
}
