package com.sample.commons.simplebankingapp.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sample.commons.simplebankingapp.model.Account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Component
@ApiModel
public class CreateAccountRequest {

    @JsonProperty("documentCode")
    @ApiModelProperty(notes = "Represents a document code", example = "5551245")
    private String documentCode;


    @NotBlank(message = "Name is required.")
    @JsonProperty("name")
    @ApiModelProperty(notes = "Represents a user name", example = "ABCD")
    private String name;

    @NotNull
    @JsonProperty("phoneNumber")
    @ApiModelProperty(notes = "Represents a user phone number", example = "+9194728453456")
    private Long phoneNumber;


    public Account toAccount() {
        return Account.builder().documentCode(documentCode).name(name).phoneNumber(phoneNumber).createdAt(LocalDateTime.now()).build();
    }

}