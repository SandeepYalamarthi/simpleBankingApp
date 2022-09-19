package com.sample.commons.simplebankingapp.service;

import com.sample.commons.simplebankingapp.model.Account;
import com.sample.commons.simplebankingapp.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;


    private Account account1;

    private Account account2;

    @BeforeEach
    void init() {
        account1 = new Account();
        account1.setAccountId(1);
        account1.setDocumentCode("10001");
        account1.setCreatedAt(LocalDateTime.now());

        account2 = new Account();
        account2.setAccountId(2);
        account2.setDocumentCode("10002");
        account2.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void save() {
        Account createAccount = new Account();
        createAccount.setDocumentCode("10001");


        when(accountRepository.save(any(Account.class))).thenReturn(account1);

        Account createdAccount = accountService.createAccount(createAccount);

        assertNotNull(createdAccount);
    }

    @Test
    void getAllAccounts() {

        List<Account> accountList = new ArrayList<>();
        accountList.add(account1);
        accountList.add(account2);

        when(accountRepository.findAll()).thenReturn(accountList);

        List<Account> accounts = accountService.getAccounts();

        assertNotNull(accounts);
        assertEquals(2, accounts.size());
    }


    @Test
    void getAccountById() {
        when(accountRepository.findById(any())).thenReturn(Optional.of(account1));
        Account existingAccount = accountService.getAccountById(account1.getAccountId());
        assertNotNull(existingAccount);
        assertThat(existingAccount.getAccountId()).isNotNull();
    }


    @Test
    void getAccountByIdForException() {


        when(accountRepository.findById(2)).thenReturn(Optional.of(account1));
        Integer id = account1.getAccountId();
        assertThrows(RuntimeException.class, () -> {
            accountService.getAccountById(id);
        });
    }

    @Test
    void updateAccount() {


        when(accountRepository.findById(any())).thenReturn(Optional.of(account1));

        when(accountRepository.save(any(Account.class))).thenReturn(account1);
        account1.setName("update_name");
        Account existingAccount = accountService.updateAccount(account1, account1.getAccountId());

        assertNotNull(existingAccount);
        assertEquals("update_name", account1.getName());
    }

    @Test
    void deleteAccount() {

        Long accountId = 1L;
        doNothing().when(accountRepository).deleteById(any());

        accountService.deleteAccount(accountId);

        verify(accountRepository, times(1)).deleteById(account1.getAccountId());
    }


}