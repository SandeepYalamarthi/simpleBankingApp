package com.sample.commons.simplebankingapp.controller;

import com.sample.commons.simplebankingapp.model.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class AccountControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void testGetAllAccounts() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Account> entity = new HttpEntity<Account>(null, headers);

        ResponseEntity<List> response =
                testRestTemplate.exchange(getRootUrl() + "/accounts", HttpMethod.GET, entity, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    void testCreateAccount() {
        HttpHeaders headers = new HttpHeaders();

        Account account = createRandomAccount();
        HttpEntity<Account> entity = new HttpEntity<>(account, headers);

        ResponseEntity<Account> response =
                testRestTemplate.exchange(getRootUrl() + "/accounts", HttpMethod.POST, entity, Account.class);
        assertNotNull(response.getBody());
    }

    @Test
    void testGetAccountById() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response =
                testRestTemplate.exchange(getRootUrl() + "/accounts", HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }


    @Test
    void testDeleteAccount() {
    }


    private Account createRandomAccount() {
        final Account account = new Account();
        account.setAccountId(Integer.valueOf(randomNumeric(4)));
        account.setName(randomAlphabetic(15));
        account.setDocumentNumber(randomAlphabetic(20));
        return account;
    }

}