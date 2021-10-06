package com.sample.commons.simplebankingapp.service;

import com.sample.commons.simplebankingapp.model.Transaction;
import com.sample.commons.simplebankingapp.repository.TransactionRepository;
import com.sample.commons.simplebankingapp.request.CreateTransactionRequest;
import com.sample.commons.simplebankingapp.response.TransactionResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  @Autowired
  TransactionRepository transactionRepository;

  public CompletionStage<TransactionResponse> createTransaction(
      CreateTransactionRequest createTransactionRequest) {

    //get transaction from request
    Transaction transaction = Transaction.from(createTransactionRequest);

    //call computeBalance
    computeBalances(transaction);

    //save transaction in db
    Transaction savedTransaction = transactionRepository.save(transaction);
    return CompletableFuture.completedFuture(TransactionResponse.from(savedTransaction));
  }

  //calculate balance amount for incomingTransaction.
  /*
   * if positive fetch  negative transactions of type 1,2,3 and clear up their balances.
   *  1 Normal Purchase
   * 2 Purchase with installments
   * 3 Withdrawal
   * 4 Credit Voucher (positive)
   */
  private void computeBalances(Transaction incomingTransaction) {

    incomingTransaction.setBalance(incomingTransaction.getAmount());

    if (!incomingTransaction.getOperationTypeId().equals(4)) {
      settlePositiveBalances(incomingTransaction);
    } else if (incomingTransaction.getOperationTypeId().equals(4)) {
      settleNegativeBalances(incomingTransaction);
    }
  }

  private void settleNegativeBalances(Transaction incomingTransaction) {
    List<Transaction> unsettledNegativeBalances = transactionRepository.unsettledNegativeBalances(
        incomingTransaction.getAccountId());

    //init balance
    Double newBalance;
    for (Transaction unsettledTrans : unsettledNegativeBalances) {
      if (incomingTransaction.getBalance() != 0.0) {
        newBalance = Double.sum(incomingTransaction.getBalance(), unsettledTrans.getBalance());
        if (newBalance < 0.0) {
          unsettledTrans.setBalance(newBalance);
          incomingTransaction.setBalance(0.0);
        } else {
          incomingTransaction.setBalance(newBalance);
          unsettledTrans.setBalance(0.0);
        }
      }
    }
    transactionRepository.saveAll(unsettledNegativeBalances);
  }

  private void settlePositiveBalances(Transaction incomingTransaction) {
    List<Transaction> unsettledPositiveBalances = transactionRepository.unsettledPositiveBalances(
        incomingTransaction.getAccountId());

    //init balance
    Double newBalance;
    for (Transaction unsettledTrans : unsettledPositiveBalances) {
      if (incomingTransaction.getBalance() != 0.0) {
        newBalance = Double.sum(incomingTransaction.getBalance(), unsettledTrans.getBalance());
        if (newBalance > 0.0) {
          unsettledTrans.setBalance(newBalance);
          incomingTransaction.setBalance(0.0);
        } else {
          incomingTransaction.setBalance(newBalance);
          unsettledTrans.setBalance(0.0);
        }
      }
    }
    transactionRepository.saveAll(unsettledPositiveBalances);
  }

  public CompletionStage<List<TransactionResponse>> getTransactions() {

    List<TransactionResponse> transactionResponses = ((List<Transaction>) transactionRepository.findAll()).stream()
        .map(TransactionResponse::from).collect(
            Collectors.toList());
    return CompletableFuture.completedFuture(transactionResponses);
  }

}