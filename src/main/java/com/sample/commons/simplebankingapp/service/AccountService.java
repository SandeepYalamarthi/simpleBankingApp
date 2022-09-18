package com.sample.commons.simplebankingapp.service;

import com.sample.commons.simplebankingapp.exception.SimpleBankingException;
import com.sample.commons.simplebankingapp.model.Account;
import com.sample.commons.simplebankingapp.repository.AccountRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account createAccount(Account account) {
        try {
            account.setCreatedAt(LocalDateTime.now());
            return accountRepository.save(account);
        } catch (DataIntegrityViolationException exception) {
            throw new SimpleBankingException("duplicate account cannot be created for same name", exception);
        }
    }

    public List<Account> getAccounts() {
        return ((List<Account>) accountRepository.findAll()).stream().collect(Collectors.toList());
    }


    public Account getAccountById(Integer accountId) {

        Optional<Account> account = accountRepository.findById(accountId);
        return account.orElseThrow(() -> new SimpleBankingException("unable to get account id"));

    }

    public Account updateAccount(Account account, Integer id) {

        Account existingAccount = accountRepository.findById(id).orElse(null);
        if (existingAccount == null) {
            throw new SimpleBankingException("wrong details provided");
        }
        existingAccount.setDocumentCode(account.getDocumentCode());
        existingAccount.setPhoneNumber(account.getPhoneNumber());
        existingAccount.setUpdatedAt(LocalDateTime.now());
        return accountRepository.save(existingAccount);
    }

    public void deleteAccount(Long id) {
        try {
            accountRepository.deleteById(Math.toIntExact(id));
        } catch (EmptyResultDataAccessException exception) {
            throw new SimpleBankingException("unable to find account with id ",exception);
        }
    }
}
