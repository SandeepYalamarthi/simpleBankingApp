package com.sample.commons.simplebankingapp.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sample.commons.simplebankingapp.model.Account;
import com.sample.commons.simplebankingapp.model.Transaction;
import com.sample.commons.simplebankingapp.model.Transaction.TransactionBuilder;
import com.sample.commons.simplebankingapp.request.CreateTransactionRequest;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {


  private Integer transactionId;

  private Integer accountId;

  private Integer operationTypeId;

  private Double amount;

  private Double balance;

  private Date eventDate;

  private Timestamp updatedAt;
  private Timestamp createdAt;


  public static TransactionResponse from(Transaction transaction) {
    return TransactionResponse.builder().
        accountId(transaction.getAccountId())
        .transactionId(transaction.getTransactionId())
        .operationTypeId(transaction.getTransactionId())
        .amount(transaction.getAmount())
        .balance(transaction.getBalance())
        .eventDate(transaction.getEventDate())
        .createdAt(transaction.getCreatedAt())
        .updatedAt(transaction.getUpdatedAt())
        .build();
  }
}
