package com.sample.commons.simpleBankingApp.service;

import com.sample.commons.simpleBankingApp.model.Transaction;
import com.sample.commons.simpleBankingApp.repository.TransactionRepository;
import com.sample.commons.simpleBankingApp.request.CreateTransactionRequest;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  @Autowired
  TransactionRepository transactionRepository;

  public CompletionStage<Integer> createTransaction(
      CreateTransactionRequest createTransactionRequest) {

    Transaction transaction = Transaction.from(createTransactionRequest);

    Transaction savedTransaction = transactionRepository.save(transaction);
    return CompletableFuture.completedFuture(savedTransaction.getTransactionId());
  }
}
