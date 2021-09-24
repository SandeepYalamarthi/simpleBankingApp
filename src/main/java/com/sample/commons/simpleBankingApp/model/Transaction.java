package com.sample.commons.simpleBankingApp.model;

import com.sample.commons.simpleBankingApp.request.CreateAccountRequest;
import com.sample.commons.simpleBankingApp.request.CreateTransactionRequest;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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


  public static Transaction from(CreateTransactionRequest createTransactionRequest) {
    return Transaction.builder().Amount(createTransactionRequest.getAmount()).accountId(createTransactionRequest.getAccountID()).operationTypeId(createTransactionRequest.getOperationTypeID()).build();
  }
}
