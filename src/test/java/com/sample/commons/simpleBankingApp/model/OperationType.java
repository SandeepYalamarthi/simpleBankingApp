package com.sample.commons.simpleBankingApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OperationsTypes")
public class OperationType {

  @Id
  @Column(name = "OperationType_ID")
  private Integer operationTypeId;

  @Column(name = "Description")
  private String description;


}
