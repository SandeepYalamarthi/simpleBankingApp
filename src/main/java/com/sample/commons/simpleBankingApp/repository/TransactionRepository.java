package com.sample.commons.simpleBankingApp.repository;

import com.sample.commons.simpleBankingApp.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction,Integer> {

}
