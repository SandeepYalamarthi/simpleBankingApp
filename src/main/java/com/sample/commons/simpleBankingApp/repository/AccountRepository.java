package com.sample.commons.simpleBankingApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sample.commons.simpleBankingApp.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

}
