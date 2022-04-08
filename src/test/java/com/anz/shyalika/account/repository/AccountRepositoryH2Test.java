package com.anz.shyalika.account.repository;


import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.anz.shyalika.account.entity.AccountEntity;

@DataJpaTest
@Sql(scripts = "/create-data.sql")
@Sql(scripts = "/cleanup-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
class AccountRepositoryH2Test {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void findAllAccountsTest() {
        List<AccountEntity> accounts = accountRepository.findAll();
        Assertions.assertEquals(4, accounts.size());
        Assertions.assertEquals("AUCurrent433", accounts.stream().findAny().get().getAccountName());
    }    
}
