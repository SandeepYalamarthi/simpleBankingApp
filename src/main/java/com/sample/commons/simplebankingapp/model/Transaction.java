package com.sample.commons.simplebankingapp.model;

import com.sample.commons.simplebankingapp.exception.SimpleBankingException;
import com.sample.commons.simplebankingapp.request.CreateTransactionRequest;
import java.sql.Timestamp;
import java.util.Arrays;
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

  private Double balance;

  @Column(name = "EventDate")
  private Date eventDate;

  private Timestamp updatedAt;
  private Timestamp createdAt;

  public static Transaction from(CreateTransactionRequest createTransactionRequest) {
    Transaction transaction = Transaction.builder().amount(createTransactionRequest.getAmount())
        .accountId(createTransactionRequest.getAccountID())
        .operationTypeId(createTransactionRequest.getOperationTypeID()).build();
    transaction.validate();
    return transaction;
  }

  private void validate() {
    if (!Arrays.asList(1, 2, 3, 4).contains(operationTypeId)) {
      throw new SimpleBankingException("invalid operation id");
    }

    if (this.operationTypeId != 4 && this.getAmount() >= 0) {
      throw new SimpleBankingException(" amount cant be positive or 0 given operation type");
    }
    if (this.operationTypeId == 4 && this.getAmount() < 0) {
      throw new SimpleBankingException("amount cant be negative  for given operation type");
    }
  }
}
