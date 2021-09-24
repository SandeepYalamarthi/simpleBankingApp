package com.sample.commons.simpleBankingApp.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CreateAccountRequest {

  @JsonProperty("document_number")
  private String documentNumber;

}
