package com.sample.commons.simpleBankingApp.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sample.commons.simpleBankingApp.model.Account;
import javax.persistence.criteria.CriteriaBuilder.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

  private Integer Id;
  @JsonProperty("document_number")
  private String documentNumber;

  private Long createdAt;
  private Long updatedAt;

  public static AccountResponse from(Account account) {
    return AccountResponse.builder().Id(account.getAccountId())
        .documentNumber(account.getDocumentNumber()).createdAt(account.getCreatedAt()).updatedAt(
            account.getUpdatedAt()).build();
  }
}
