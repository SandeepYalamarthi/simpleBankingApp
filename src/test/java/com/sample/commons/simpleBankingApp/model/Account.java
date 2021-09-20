package com.sample.commons.simpleBankingApp.model;

import java.beans.ConstructorProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Accounts")
public class Account {

  @Id
  @Column(name = "Account_ID")
  private Integer accountId;
  @Column(name = "Document_Number")
  private String documentNumber;

}
