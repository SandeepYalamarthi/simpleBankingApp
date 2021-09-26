package com.sample.commons.simpleBankingApp.service;

import com.sample.commons.simpleBankingApp.model.Account;
import com.sample.commons.simpleBankingApp.repository.AccountRepository;
import com.sample.commons.simpleBankingApp.request.CreateAccountRequest;
import com.sample.commons.simpleBankingApp.response.AccountResponse;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  @Autowired
  AccountRepository accountRepository;

  public CompletionStage<Integer> createAccount(CreateAccountRequest createAccountRequest) {

    Account account = Account.from(createAccountRequest);
    Account savedAccount = accountRepository.save(account);
    return CompletableFuture.completedFuture(savedAccount.getAccountId());

  }

  public List<AccountResponse> getAccounts() {

    return ((List<Account>) accountRepository.findAll()).stream()
        .map(AccountResponse::from).collect(
            Collectors.toList());
  }


  public CompletionStage<AccountResponse> getAccountById(Integer accountId) {

    Optional<Account> account = accountRepository.findById(accountId);
    return CompletableFuture.completedFuture(AccountResponse.from(account.get()));

  }
}
