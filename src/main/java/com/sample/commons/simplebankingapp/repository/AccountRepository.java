package com.sample.commons.simplebankingapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sample.commons.simplebankingapp.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

}
