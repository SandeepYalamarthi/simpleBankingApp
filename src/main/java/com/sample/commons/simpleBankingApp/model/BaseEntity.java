package com.sample.commons.simpleBankingApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

  private Long updatedAt;
  private Long createdAt;

}
