package com.sample.commons.simplebankingapp.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTransactionRequest {

  @JsonProperty("account_id")
  private Integer accountID;

  @JsonProperty("operation_type_id")
  private Integer operationTypeID;

  @JsonProperty("amount")
  private Double amount;

}