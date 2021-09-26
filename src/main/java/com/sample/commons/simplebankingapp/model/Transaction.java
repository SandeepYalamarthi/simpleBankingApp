package com.sample.commons.simplebankingapp.model;

import com.sample.commons.simplebankingapp.request.CreateTransactionRequest;
import java.sql.Timestamp;
import java.util.Date;
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

@Entity
@Table(name = "Transactions")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Transaction_ID")
  private Integer transactionId;

  @Column(name = "Account_ID")
  private Integer accountId;

  @Column(name = "OperationType_ID")
  private Integer operationTypeId;

  @Column(name = "Amount")
  private Double amount;

  @Column(name = "EventDate")
  private Date eventDate;

  private Timestamp updatedAt;
  private Timestamp createdAt;

  public static Transaction from(CreateTransactionRequest createTransactionRequest) {
    return Transaction.builder().amount(createTransactionRequest.getAmount()).accountId(createTransactionRequest.getAccountID()).operationTypeId(createTransactionRequest.getOperationTypeID()).build();
  }
}
