package com.sample.commons.simplebankingapp.controller;

import com.sample.commons.simplebankingapp.model.Account;
import com.sample.commons.simplebankingapp.service.AccountService;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        try {
            log.info("adding account");
            return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(account));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable("accountId") Integer accountId) {
        try {
            log.info("get  account by id");
            return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountById(accountId));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        try {
            log.info("get  account by id");
            return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccounts());
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{accountId}")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account, @PathVariable("accountId") Integer accountId) {

        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountService.updateAccount(account, accountId));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable Long id) {
        try {
            accountService.deleteAccount(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
