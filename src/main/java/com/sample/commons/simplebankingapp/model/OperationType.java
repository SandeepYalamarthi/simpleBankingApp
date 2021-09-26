package com.sample.commons.simplebankingapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OperationsTypes")
public class OperationType {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "OperationType_ID")
  private Integer operationTypeId;

  @Column(name = "Description")
  private String description;


}
