package com.sample.commons.simplebankingapp.repository;

import com.sample.commons.simplebankingapp.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction,Integer> {

}
