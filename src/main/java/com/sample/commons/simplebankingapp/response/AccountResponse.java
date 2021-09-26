package com.sample.commons.simplebankingapp.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sample.commons.simplebankingapp.model.Account;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

  private Integer accountId;
  @JsonProperty("document_number")
  private String documentNumber;

  private Timestamp createdAt;
  private Timestamp updatedAt;

  public static AccountResponse from(Account account) {
    return AccountResponse.builder().accountId(account.getAccountId())
        .documentNumber(account.getDocumentNumber()).createdAt(account.getCreatedAt()).updatedAt(
            account.getUpdatedAt()).build();
  }
}
