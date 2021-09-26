package com.sample.commons.simplebankingapp.controller;


import com.sample.commons.simplebankingapp.request.CreateTransactionRequest;
import com.sample.commons.simplebankingapp.service.TransactionService;
import java.util.concurrent.CompletionStage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@Slf4j
public class TransactionController {

  @Autowired
  TransactionService transactionService;

  public CompletionStage<ResponseEntity<String>> createTransaction(
      @RequestBody CreateTransactionRequest createTransactionRequest
  ) {
    log.info("adding transaction");
    return transactionService.createTransaction(createTransactionRequest)
        .thenApply(transactionId -> ResponseEntity.status(HttpStatus.CREATED)
            .header("transactionId", String.valueOf(transactionId)).body("")).toCompletableFuture();
  }
}
