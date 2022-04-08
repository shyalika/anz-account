package com.anz.shyalika.account.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anz.shyalika.account.entity.AccountEntity;
import com.anz.shyalika.account.entity.TransactionEntity;
import com.anz.shyalika.account.model.Account;
import com.anz.shyalika.account.model.Transaction;
import com.anz.shyalika.account.repository.AccountRepository;
import com.anz.shyalika.account.repository.TransactionRepository;
import com.anz.shyalika.account.service.AccountService;
import com.anz.shyalika.account.util.TypeConverter;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementation class of the {@link AccountService} provides implementations of the APIs process
 * request related to Accounts and Transactions.
 * 
 * @author Shyalika Benthotage
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * {@inheritDoc}
     */
    public List<Account> getAccounts() {
        log.info("Processing request to get accounts from database.");
        // Assumption- Pagination is not required at this stage
        List<AccountEntity> accountEntities = accountRepository.findAll();

        log.debug("Found {} account records.", accountEntities.size());
        // NOTE: Uses the modelMapper instance to map Entity to DTO. For the current stage the code
        // looks like an overkill, however it is used here to show the potential when it comes to a
        // larger number of simple column mappings that otherwise will be using setters
        return accountEntities.stream().map(this::convertAccountToDto).collect(Collectors.toList());

    }

    /**
     * {@inheritDoc}
     */
    public List<Transaction> getTransactions(BigInteger accountNumber) {
        // Assumption- Pagination is not required at this stage

        log.info(
                "Processing request to get transaction for the account number {} from database.",
                accountNumber);

        if (accountNumber != null) {
            List<TransactionEntity> transactionEntities = transactionRepository.findTransactionByAccount_AccountNumber(
                    accountNumber);

            log.debug(
                    "Found {} transaction records for the account number {}.",
                    transactionEntities.size(), accountNumber);

            return transactionEntities.stream().map(this::convertTransactionToDto).collect(
                    Collectors.toList());
        } else {
            log.info("Account number is null");
        }

        return new ArrayList<Transaction>();
    }

    /**
     * Post Construct method to configure ModelMapper instant to map Enum values and Date formats
     * correctly to the DTOs
     */
    @PostConstruct
    private void init() {
        log.info("Initializing the AccountService.");

        // Map AccountType.getName() to String
        PropertyMap<AccountEntity, Account> accountMap = new PropertyMap<>() {

            protected void configure() {
                using(TypeConverter.accountTypeEnumConverter).map(
                        source.getAccountType()).setAccountType(null);
            }
        };

        // Map Currency Enum to String
        PropertyMap<AccountEntity, Account> currencyMap = new PropertyMap<>() {

            protected void configure() {
                using(TypeConverter.currencyEnumConverter).map(source.getCurrency()).setCurrency(
                        null);
            }
        };

        // Map TransactionType Enum to String
        PropertyMap<TransactionEntity, Transaction> tranTypeMap = new PropertyMap<>() {

            protected void configure() {
                using(TypeConverter.transactionTypeEnumConverter).map(
                        source.getTransactionType()).setDebitCredit(null);
            }
        };

        // Currency Enum of the Account to String for Transaction DTO
        PropertyMap<TransactionEntity, Transaction> transCurrencyMap = new PropertyMap<>() {

            protected void configure() {
                using(TypeConverter.currencyEnumConverter).map(
                        source.getAccount().getCurrency()).setCurrency(null);
            }
        };

        // Adds the configured mappings
        modelMapper.addMappings(accountMap);
        modelMapper.addMappings(currencyMap);
        modelMapper.addMappings(tranTypeMap);
        modelMapper.addMappings(transCurrencyMap);

        // FIXME A warning for An illegal reflective access operation is displayed on Springboot
        // log for the java.util.Date mapping. Initial research did not help solving this issue
        // given the limited time frame.
        modelMapper.getTypeMap(TransactionEntity.class, Transaction.class).addMappings(
                mapper -> mapper.using(TypeConverter.formatDateLong).map(
                        TransactionEntity::getValueDate, Transaction::setValueDate));
        modelMapper.getTypeMap(AccountEntity.class, Account.class).addMappings(
                mapper -> mapper.using(TypeConverter.formatDate).map(
                        AccountEntity::getBalanceDate, Account::setBalanceDate));

    }

    /**
     * Uses the model mapper to convert an instance of {@link AccountEntity} to an {@link Account}
     * 
     * @param accountEntity
     *            source object
     * @return an instance of Account DTO
     */
    private Account convertAccountToDto(AccountEntity accountEntity) {
        return modelMapper.map(accountEntity, Account.class);
    }

    /**
     * Uses the model mapper to convert an instance of {@link TransactionEntity} to an
     * {@link Transaction}
     * 
     * @param transactionEntity
     *            source object
     * @return an instance of Transaction DTO
     */
    private Transaction convertTransactionToDto(TransactionEntity transactionEntity) {
        return modelMapper.map(transactionEntity, Transaction.class);
    }

}
