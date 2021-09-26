package com.sample.commons.simplebankingapp.model;

import com.sample.commons.simplebankingapp.request.CreateAccountRequest;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Account_ID")
  private Integer accountId;
  @Column(name = "Document_Number")
  private String documentNumber;

  private Timestamp updatedAt;
  private Timestamp createdAt;


  public static Account from(CreateAccountRequest accountRequest) {
    return Account.builder().documentNumber(accountRequest.getDocumentNumber()).build();
  }

}
