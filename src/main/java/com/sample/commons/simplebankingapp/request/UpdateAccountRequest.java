package com.sample.commons.simplebankingapp.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sample.commons.simplebankingapp.model.Account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;

@Data
@Component

@ApiModel
public class UpdateAccountRequest {

    @JsonProperty("documentCode")
    @ApiModelProperty(notes = "Represents a document code", example = "5551245")
    private String documentCode;

    @ApiModelProperty(notes = "Represents a user phone number", example = "+9194728453456")
    private Long phoneNumber;


    public Account toAccount() {
        return Account.builder().documentCode(documentCode).phoneNumber(phoneNumber).build();
    }

}