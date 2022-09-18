package com.sample.commons.simplebankingapp.service;

import com.sample.commons.simplebankingapp.exception.SimpleBankingException;
import com.sample.commons.simplebankingapp.model.Account;
import com.sample.commons.simplebankingapp.repository.AccountRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account createAccount(Account account) {

        account.setCreatedAt(LocalDateTime.now());
        Account savedAccount = accountRepository.save(account);
        return savedAccount;

    }

    public List<Account> getAccounts() {
        List<Account> accountResponses = ((List<Account>) accountRepository.findAll()).stream().collect(Collectors.toList());
        return accountResponses;
    }


    public Account getAccountById(Integer accountId) {

        Optional<Account> account = accountRepository.findById(accountId);
        return account.orElseThrow(() ->
                new SimpleBankingException("unable to get account id")
        );

    }

    public Account updateAccount(Account account, Integer id) {
        if (account.getAccountId() != id) {
            throw new SimpleBankingException("body path mismatch for account");
        }
        Account existingAccount = accountRepository.findById(id).get();
        existingAccount.setDocumentNumber(account.getDocumentNumber());
        existingAccount.setName(account.getName());
        existingAccount.setPhoneNumber(account.getPhoneNumber());
        return accountRepository.save(existingAccount);
    }

    public void deleteAccount(Long id) {
        Account existingAccount = accountRepository.findById(Math.toIntExact(id)).get();
        accountRepository.delete(existingAccount);
    }
}
