package com.anz.shyalika.account.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.anz.shyalika.account.entity.AccountEntity;
import com.anz.shyalika.account.entity.TransactionEntity;
import com.anz.shyalika.account.model.Account;
import com.anz.shyalika.account.model.Transaction;
import com.anz.shyalika.account.repository.AccountRepository;
import com.anz.shyalika.account.repository.TransactionRepository;
import com.anz.shyalika.account.service.AccountService;
import com.anz.shyalika.account.util.AccountType;
import com.anz.shyalika.account.util.Currency;
import com.anz.shyalika.account.util.TransactionType;

@ExtendWith(SpringExtension.class)
class AccountServiceImplTest {

    private static final String TEST_ACCOUNT_NUMBER = "321143048";

    @InjectMocks
    AccountService accountService = new AccountServiceImpl();

    @Mock
    AccountRepository accountRepository;

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    ModelMapper modelMapper;

    @Test
    void testGetAccounts_NoResults() {

        // AccountsRepository returns nothing
        List<Account> accounts = accountService.getAccounts();
        Assertions.assertTrue(accounts.isEmpty());

    }

    @Test
    void testGetAccounts_Success() {

        List<AccountEntity> accountEntities = new ArrayList<>();
        AccountEntity accountEntity = getAccountEntity();

        accountEntities.add(accountEntity);

        Mockito.when(accountRepository.findAll()).thenReturn(accountEntities);

        // AccountsRepository returns nothing
        List<Account> accounts = accountService.getAccounts();
        Assertions.assertEquals(1, accounts.size());

    }

    private AccountEntity getAccountEntity() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(new BigInteger(TEST_ACCOUNT_NUMBER));
        accountEntity.setAccountName("AUCurrent433");
        accountEntity.setAccountType(AccountType.SAVINGS);
        accountEntity.setBalanceDate(new Date());
        accountEntity.setCurrency(Currency.AUD);
        accountEntity.setOpeningAvailableBalance(new BigDecimal("10000.00"));
        return accountEntity;
    }

    @Test
    void testGetTransactions_NoResults() {

        List<TransactionEntity> transactionEntities = new ArrayList<>();
        TransactionEntity transactionEntity = getTransactionEntity();

        transactionEntities.add(transactionEntity);

        Mockito.when(
                transactionRepository.findTransactionByAccount_AccountNumber(
                        Mockito.any())).thenReturn(transactionEntities);

        // null account number supplied
        List<Transaction> transactions = accountService.getTransactions(null);
        Assertions.assertTrue(transactions.isEmpty());

    }

    @Test
    void testGetTransactions_NoData() {

        Mockito.when(
                transactionRepository.findTransactionByAccount_AccountNumber(
                        Mockito.any())).thenReturn(new ArrayList<TransactionEntity>());

        List<Transaction> transactions = accountService.getTransactions(
                new BigInteger(TEST_ACCOUNT_NUMBER));
        Assertions.assertTrue(transactions.isEmpty());
    }

    @Test
    void testGetTransactions_Success() {

        List<TransactionEntity> transactionEntities = new ArrayList<>();
        TransactionEntity transactionEntity = getTransactionEntity();

        transactionEntities.add(transactionEntity);

        Mockito.when(
                transactionRepository.findTransactionByAccount_AccountNumber(
                        Mockito.any())).thenReturn(transactionEntities);

        List<Transaction> transactions = accountService.getTransactions(
                new BigInteger(TEST_ACCOUNT_NUMBER));
        Assertions.assertEquals(1, transactions.size());
    }

    private TransactionEntity getTransactionEntity() {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAccount(getAccountEntity());
        transactionEntity.setTransactionNarrative("Narrative");
        transactionEntity.setTransactionType(TransactionType.CREDIT);
        transactionEntity.setValueDate(new Date());
        transactionEntity.setUuid(UUID.randomUUID().toString());
        transactionEntity.setCreditAmount(new BigDecimal("10000.00"));
        return transactionEntity;
    }

}
