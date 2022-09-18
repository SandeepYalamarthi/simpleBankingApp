package com.sample.commons.simplebankingapp.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Entity
@Table(name = "accounts")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Account_ID")
    private Integer accountId;

    @Column(name = "Document_Number")
    private String documentNumber;


    @NotBlank(message = "Name is mandatory")
    @Column(name = "Name")
    private String name;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    private LocalDateTime createdAt;


}
