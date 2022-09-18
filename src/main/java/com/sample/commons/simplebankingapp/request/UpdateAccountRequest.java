package com.sample.commons.simplebankingapp.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sample.commons.simplebankingapp.model.Account;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;

@Data
@Component
public class UpdateAccountRequest {

    @JsonProperty("documentCode")
    private String documentCode;


    private Long phoneNumber;


    public Account toAccount() {
        return Account.builder().documentCode(documentCode).phoneNumber(phoneNumber).build();
    }

}