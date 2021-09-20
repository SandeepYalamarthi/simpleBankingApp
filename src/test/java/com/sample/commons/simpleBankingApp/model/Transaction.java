package com.sample.commons.simpleBankingApp.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Transactions")
public class Transaction {
  @Id
  @Column(name = "Transaction_ID")
  private Integer transactionId;

  @Column(name = "Account_ID")
  private Integer accountId;

  @Column(name = "OperationType_ID")
  private Integer operationTypeId;

  @Column(name = "Amount")
  private Double Amount;

  @Column(name = "EventDate")
  private Date eventDate;


}
