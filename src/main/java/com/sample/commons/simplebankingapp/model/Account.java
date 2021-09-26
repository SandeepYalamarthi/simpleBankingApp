package com.sample.commons.simplebankingapp.model;

import com.sample.commons.simplebankingapp.request.CreateAccountRequest;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Accounts")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account extends  BaseEntity{

  @Id
  @Column(name = "Account_ID")
  private Integer accountId;
  @Column(name = "Document_Number")
  private String documentNumber;


  public static Account from(CreateAccountRequest accountRequest) {
    return Account.builder().documentNumber(accountRequest.getDocumentNumber()).build();
  }

}
