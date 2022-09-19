package com.sample.commons.simplebankingapp.controller;

import com.sample.commons.simplebankingapp.model.Account;
import com.sample.commons.simplebankingapp.request.CreateAccountRequest;
import com.sample.commons.simplebankingapp.request.UpdateAccountRequest;
import com.sample.commons.simplebankingapp.service.AccountService;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest) {
        Account createdAccount = accountService.createAccount(createAccountRequest.toAccount());
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable("accountId") Integer accountId) {
        return new ResponseEntity<>(accountService.getAccountById(accountId), OK);
    }


    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        return new ResponseEntity<>(accountService.getAccounts(), OK);
    }


    @PutMapping("/{accountId}")
    public ResponseEntity<Account> updateAccount(@Valid @RequestBody UpdateAccountRequest updateAccountRequest, @PathVariable("accountId") Integer accountId) {
        return new ResponseEntity<>(accountService.updateAccount(updateAccountRequest.toAccount(), accountId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable Long id) {

        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
