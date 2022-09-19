package com.sample.commons.simplebankingapp.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.commons.simplebankingapp.model.Account;
import com.sample.commons.simplebankingapp.request.CreateAccountRequest;
import com.sample.commons.simplebankingapp.request.UpdateAccountRequest;
import com.sample.commons.simplebankingapp.service.AccountService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    @ApiOperation(value = "Use the Accounts API as a POST request to create a new Account.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = Integer.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Internal Error", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = String.class) })
    public ResponseEntity<Account> createAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest) {
        Account createdAccount = accountService.createAccount(createAccountRequest.toAccount());
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable("accountId") Integer accountId) {
        return new ResponseEntity<>(accountService.getAccountById(accountId), OK);
    }


    @GetMapping
    @ApiOperation(value = "Use the Accounts API as a Get request to view the account details.")
    @ApiResponses(value = {  @ApiResponse(code = 200, message = "OK", responseContainer = "List",response = Account.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Internal Error", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = String.class) })
    public ResponseEntity<List<Account>> getAccounts() {
        return new ResponseEntity<>(accountService.getAccounts(), OK);
    }


    @PutMapping("/{accountId}")
    @ApiOperation(value = "Use the Accounts API as a PUT request to update the account details.")
    @ApiResponses(value = {  @ApiResponse(code = 200, message = "OK",response = Account.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Internal Error", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = String.class) })
    public ResponseEntity<Account> updateAccount(@Valid @RequestBody UpdateAccountRequest updateAccountRequest, @PathVariable("accountId") Integer accountId) {
        return new ResponseEntity<>(accountService.updateAccount(updateAccountRequest.toAccount(), accountId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable Long id) {

        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
