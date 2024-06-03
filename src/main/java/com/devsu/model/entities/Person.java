package com.devsu.model.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("Person")
@Getter
@Setter
@Builder
public class Person {

  @Id
  @Column("id")
  private Integer id;

  @Column("name")
  private String name;

  @Column("gender")
  private String gender;

  @Column("age")
  private Integer age;

  @Column("identification")
  private String identification;

  @Column("address")
  private String address;

  @Column("phone")
  private String phone;

}
