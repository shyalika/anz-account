package com.anz.shyalika.account.service;

import java.math.BigInteger;
import java.util.List;

import com.anz.shyalika.account.model.Account;
import com.anz.shyalika.account.model.Transaction;

/**
 * AccountService interface provides APIs process request related to Accounts and Transactions.
 * 
 * @author Shyalika Benthotage
 *
 */
public interface AccountService {
    
    /**
     * Returns a list of accounts. NOTE: Pagination is out of scope at this stage
     * @return an array list of {@link Account} DTOs
     */
    public List<Account> getAccounts();

    /**
     * Returns a list of transactions for the given account number. NOTE: Pagination is out of scope at this stage
     * @return an array list of {@link Transaction} DTOs
     */
    public List<Transaction> getTransactions(BigInteger accountNumber);
}
