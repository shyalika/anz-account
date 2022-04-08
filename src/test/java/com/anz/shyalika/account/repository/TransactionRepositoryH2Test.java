package com.anz.shyalika.account.repository;


import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.anz.shyalika.account.entity.TransactionEntity;

@DataJpaTest
@Sql(scripts = "/create-data.sql")
@Sql(scripts = "/cleanup-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
class TransactionRepositoryH2Test {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void findAllTransactionsTest() {
        List<TransactionEntity> transactions = transactionRepository.findAll();
        Assertions.assertEquals(8, transactions.size());
        Assertions.assertEquals("AUCurrent433", transactions.stream().findAny().get().getAccount().getAccountName());
    }   
    
    @Test
    void findTransactionsByAccountNumber_FoundTest() {
        List<TransactionEntity> transactions = transactionRepository.findTransactionByAccount_AccountNumber(new BigInteger("321143048"));
        Assertions.assertEquals(2, transactions.size());
        Assertions.assertEquals("AUCurrent433", transactions.stream().findAny().get().getAccount().getAccountName());
    }  
    
    @Test
    void findTransactionsByAccountNumber_Found2Test() {
        List<TransactionEntity> transactions = transactionRepository.findTransactionByAccount_AccountNumber(new BigInteger("347786244"));
        Assertions.assertEquals(3, transactions.size());
        Assertions.assertEquals("SGCurrent166", transactions.stream().findAny().get().getAccount().getAccountName());
    }  
    
    @Test
    void findTransactionsByAccountNumber_NotFoundTest() {
        List<TransactionEntity> transactions = transactionRepository.findTransactionByAccount_AccountNumber(new BigInteger("347786241"));
        Assertions.assertEquals(0, transactions.size());
    }  
}
