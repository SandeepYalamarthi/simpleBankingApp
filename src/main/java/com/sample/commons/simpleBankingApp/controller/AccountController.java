package com.sample.commons.simpleBankingApp.controller;

import com.sample.commons.simpleBankingApp.request.CreateAccountRequest;
import com.sample.commons.simpleBankingApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  @Autowired
  AccountService accountService;

  @PostMapping
  public void createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
    accountService.createAccount(createAccountRequest);
  }

  @GetMapping
  public void getAccounts() {
    accountService.getAccounts();
  }

  @GetMapping
  public void getAccount(@PathVariable("accountId") Integer accountId) {
    accountService.getAccountById(accountId);
  }
}
