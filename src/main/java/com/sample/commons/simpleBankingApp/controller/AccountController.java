package com.sample.commons.simpleBankingApp.controller;

import com.sample.commons.simpleBankingApp.model.Account;
import com.sample.commons.simpleBankingApp.request.CreateAccountRequest;
import com.sample.commons.simpleBankingApp.response.AccountResponse;
import com.sample.commons.simpleBankingApp.service.AccountService;
import java.util.concurrent.CompletionStage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountController {

  @Autowired
  AccountService accountService;

  @PostMapping
  public CompletionStage<ResponseEntity<String>> createAccount(
      @RequestBody CreateAccountRequest createAccountRequest) {
    log.info("adding account");

    return accountService.createAccount(createAccountRequest)
        .thenApply(accountId -> ResponseEntity.status(HttpStatus.CREATED)
            .header("accountId", String.valueOf(accountId)).body("")).toCompletableFuture();

  }

  @GetMapping
  public void getAccounts() {
    accountService.getAccounts();
  }

  @GetMapping
  public CompletionStage<ResponseEntity<AccountResponse>> getAccount(
      @PathVariable("accountId") Integer accountId) {
    log.info("get  account by id");

    return accountService.getAccountById(accountId)
        .thenApply(accountResponse -> ResponseEntity.status(
            HttpStatus.OK).body(accountResponse))
        .toCompletableFuture();
  }
}
