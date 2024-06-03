package com.devsu.model.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("Client")
@Getter
@Setter
@Builder
public class Client {

  @Id
  @Column("id")
  private Integer id;

  @Column("password")
  private String password;

  @Column("status")
  private String status;

  @Column("person_id")
  private Integer personId;

}

