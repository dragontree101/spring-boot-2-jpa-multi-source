package com.example.jpa.demo.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 2017/8/8.
 */
@Data
@Builder
@Entity
@Table(name = "demo_table")
@NoArgsConstructor
@AllArgsConstructor
public class DemoInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, name = "name")
  private String name;

  @Column(nullable = false, name = "age")
  private int age;

  @Column(nullable = false, name = "score")
  private int score;
}
