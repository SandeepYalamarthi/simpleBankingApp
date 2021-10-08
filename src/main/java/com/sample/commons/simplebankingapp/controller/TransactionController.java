package com.sample.commons.simplebankingapp.controller;


import com.sample.commons.simplebankingapp.request.CreateTransactionRequest;
import com.sample.commons.simplebankingapp.response.TransactionResponse;
import com.sample.commons.simplebankingapp.service.TransactionService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@Slf4j
public class TransactionController {

  @Autowired
  TransactionService transactionService;

  @PostMapping
  public CompletionStage<ResponseEntity<TransactionResponse>> createTransaction(
      @RequestBody CreateTransactionRequest createTransactionRequest
  ) {
    log.info("adding transaction");
    return CompletableFuture.supplyAsync(() ->
            transactionService.createTransaction(createTransactionRequest))
        .thenApply(transactionResponse -> ResponseEntity.status(HttpStatus.CREATED)
            .header("transactionId", String.valueOf(transactionResponse.getTransactionId()))
            .body(transactionResponse)).toCompletableFuture();
  }

  @GetMapping
  public CompletionStage<ResponseEntity<List<TransactionResponse>>> getTransactions() {
    log.info("getting transaction");
    return transactionService.getTransactions()
        .thenApply(transactionResponseList -> ResponseEntity.status(HttpStatus.OK)
            .body(transactionResponseList)).toCompletableFuture();
  }
}
