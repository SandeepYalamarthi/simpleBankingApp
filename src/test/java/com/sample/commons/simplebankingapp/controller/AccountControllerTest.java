package com.sample.commons.simplebankingapp.controller;

import com.sample.commons.simplebankingapp.model.Account;
import com.sample.commons.simplebankingapp.request.CreateAccountRequest;
import com.sample.commons.simplebankingapp.request.UpdateAccountRequest;
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
 class AccountControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
     void testGetAllAccounts() {
        HttpHeaders headers = new HttpHeaders();


        //create accounts
        Account randomAccount = createRandomAccount();
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setDocumentCode(randomAccount.getDocumentCode());
        createAccountRequest.setPhoneNumber(randomAccount.getPhoneNumber());
        createAccountRequest.setName(randomAccount.getName());

        HttpEntity<CreateAccountRequest> createAccountRequestHttpEntity = new HttpEntity<>(createAccountRequest, headers);

        ResponseEntity<Account> createResponse =
                testRestTemplate.exchange(getRootUrl() + "/accounts", HttpMethod.POST, createAccountRequestHttpEntity, Account.class);


        Account randomAccount2 = createRandomAccount();
        CreateAccountRequest createAccountRequest2 = new CreateAccountRequest();
        createAccountRequest2.setDocumentCode(randomAccount2.getDocumentCode());
        createAccountRequest2.setPhoneNumber(randomAccount2.getPhoneNumber());
        createAccountRequest2.setName(randomAccount2.getName());

        HttpEntity<CreateAccountRequest> createAccountRequestHttpEntity2 = new HttpEntity<>(createAccountRequest2, headers);

        ResponseEntity<Account> createResponse2 =
                testRestTemplate.exchange(getRootUrl() + "/accounts", HttpMethod.POST, createAccountRequestHttpEntity2, Account.class);



        //get accounts
        HttpEntity<Account> entity = new HttpEntity<Account>(null, headers);

        ResponseEntity<List> response =
                testRestTemplate.exchange(getRootUrl() + "/accounts", HttpMethod.GET, entity, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotEquals(0, response.getBody().size());
    }


    @Test
    void testCreateAccount_validAccount() {
        HttpHeaders headers = new HttpHeaders();

        Account randomAccount = createRandomAccount();
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setDocumentCode(randomAccount.getDocumentCode());
        createAccountRequest.setPhoneNumber(randomAccount.getPhoneNumber());
        createAccountRequest.setName(randomAccount.getName());

        HttpEntity<CreateAccountRequest> entity = new HttpEntity<>(createAccountRequest, headers);

        ResponseEntity<Account> response =
                testRestTemplate.exchange(getRootUrl() + "/accounts", HttpMethod.POST, entity, Account.class);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testCreateAccount_invalidAccountRequest() {
        HttpHeaders headers = new HttpHeaders();

        Account randomAccount = createRandomAccount();
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setDocumentCode(randomAccount.getDocumentCode());
        createAccountRequest.setPhoneNumber(randomAccount.getPhoneNumber());

        HttpEntity<CreateAccountRequest> entity = new HttpEntity<>(createAccountRequest, headers);

        ResponseEntity<Account> response =
                testRestTemplate.exchange(getRootUrl() + "/accounts", HttpMethod.POST, entity, Account.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    void testGetAccountById() {
        HttpHeaders headers = new HttpHeaders();

        //first create account
        Account randomAccount = createRandomAccount();
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setDocumentCode(randomAccount.getDocumentCode());
        createAccountRequest.setPhoneNumber(randomAccount.getPhoneNumber());
        createAccountRequest.setName(randomAccount.getName());
        HttpEntity<CreateAccountRequest> createEntity = new HttpEntity<CreateAccountRequest>(createAccountRequest, headers);

        ResponseEntity<Account> response =
                testRestTemplate.exchange(getRootUrl() + "/accounts", HttpMethod.POST, createEntity, Account.class);

        HttpEntity<String> getEntity = new HttpEntity<String>(null, headers);

        Integer createdAccountId = response.getBody().getAccountId();
        ResponseEntity<Account> getResponse =
                testRestTemplate.exchange(getRootUrl() + "/accounts/" + createdAccountId, HttpMethod.GET, getEntity, Account.class);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals(getResponse.getBody().getAccountId(), createdAccountId);

    }


    @Test
    void updateAccount() {
        HttpHeaders headers = new HttpHeaders();


        //first create account
        Account randomAccount = createRandomAccount();
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setDocumentCode(randomAccount.getDocumentCode());
        createAccountRequest.setPhoneNumber(randomAccount.getPhoneNumber());
        createAccountRequest.setName(randomAccount.getName());

        HttpEntity<CreateAccountRequest> createEntity = new HttpEntity<>(createAccountRequest, headers);

        ResponseEntity<Account> createdResponse =
                testRestTemplate.exchange(getRootUrl() + "/accounts", HttpMethod.POST, createEntity, Account.class);


        UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest();
        updateAccountRequest.setDocumentCode(randomAlphabetic(20));
        updateAccountRequest.setPhoneNumber(Long.valueOf(randomNumeric(10)));


        HttpEntity<UpdateAccountRequest> updateAccountEntity = new HttpEntity<>(updateAccountRequest, headers);

        Account createdAccount = createdResponse.getBody();

        ResponseEntity<Account> updateResponse =
                testRestTemplate.exchange(getRootUrl() + "/accounts/" + createdAccount.getAccountId(), HttpMethod.PUT, updateAccountEntity, Account.class);
        Account updatedAccount = updateResponse.getBody();


        assertEquals(createdAccount.getAccountId(), updatedAccount.getAccountId());
        assertEquals(createdAccount.getName(), updatedAccount.getName());
        assertNotNull(updatedAccount.getUpdatedAt());


        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());


    }

    @Test
    void testDeleteAccount() {
        HttpHeaders headers = new HttpHeaders();


        //first create account
        Account randomAccount = createRandomAccount();
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setDocumentCode(randomAccount.getDocumentCode());
        createAccountRequest.setPhoneNumber(randomAccount.getPhoneNumber());
        createAccountRequest.setName(randomAccount.getName());

        HttpEntity<CreateAccountRequest> createEntity = new HttpEntity<>(createAccountRequest, headers);

        ResponseEntity<Account> createdResponse =
                testRestTemplate.exchange(getRootUrl() + "/accounts", HttpMethod.POST, createEntity, Account.class);

        Account createdAccount = createdResponse.getBody();


        //delete account

        HttpEntity<String> deleteEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> deleteResponse =
                testRestTemplate.exchange(getRootUrl() + "/accounts/" + createdAccount.getAccountId(), HttpMethod.DELETE, createEntity, String.class);

        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());

        //get account by id

        HttpEntity<String> getEntity = new HttpEntity<>(null, headers);

        ResponseEntity<Account> getResponse =
                testRestTemplate.exchange(getRootUrl() + "/accounts/" + createdAccount.getAccountId(), HttpMethod.GET, getEntity, Account.class);
        assertNull(getResponse.getBody().getAccountId());


    }


    private Account createRandomAccount() {
        final Account account = new Account();
        account.setAccountId(Integer.valueOf(randomNumeric(4)));
        account.setName(randomAlphabetic(15));
        account.setDocumentCode(randomAlphabetic(20));
        account.setPhoneNumber(Long.valueOf(randomNumeric(10)));
        return account;
    }

}