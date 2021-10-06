package com.sample.commons.simplebankingapp.repository;

import com.sample.commons.simplebankingapp.model.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {


  @Query(value = "SELECT *  FROM Transactions t  WHERE t.OPERATION_TYPE_ID !=4  and t.balance!=0 and t.ACCOUNT_ID=:accountId ", nativeQuery = true)
  public List<Transaction> unsettledNegativeBalances(Integer accountId);


  @Query(value = "SELECT *  FROM Transactions t  WHERE t.OPERATION_TYPE_ID =4  and t.balance!=0 and t.ACCOUNT_ID=:accountId ", nativeQuery = true)
  public List<Transaction> unsettledPositiveBalances(Integer accountId);

}
