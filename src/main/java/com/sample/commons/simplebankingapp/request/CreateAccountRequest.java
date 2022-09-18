package com.sample.commons.simplebankingapp.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sample.commons.simplebankingapp.model.Account;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Component
public class CreateAccountRequest {

    @JsonProperty("documentCode")
    private String documentCode;


    @NotBlank(message = "Name is required.")
    private String name;

    @NotNull
    private Long phoneNumber;


    public Account toAccount() {
        return Account.builder().documentCode(documentCode).name(name).phoneNumber(phoneNumber).createdAt(LocalDateTime.now()).build();
    }

}