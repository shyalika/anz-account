package com.anz.shyalika.account.resource.v1;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anz.shyalika.account.exception.ValidationException;
import com.anz.shyalika.account.model.Transaction;
import com.anz.shyalika.account.service.AccountService;

import lombok.extern.slf4j.Slf4j;

/**
 * Rest controller for the Transaction resource. Provides Get methods for the resource only.
 * 
 * @author Shyalika Benthotage
 */
@RestController
@RequestMapping(
        path = "/transactions",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = {
            RequestMethod.GET
        })
@Slf4j
public class TransactionResource {

    @Autowired
    private AccountService accountService;

    /**
     * GET method to return a list of transactions for a given account number
     * 
     * @param accountNumber
     * @return
     * @throws Exception
     */
    @GetMapping("/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountNumber(@PathVariable BigInteger accountNumber)
            throws Exception {
        log.info("Request recived to get transations for account number {}", accountNumber);

        if (accountNumber == null || BigInteger.ZERO.equals(accountNumber)) {
            throw new ValidationException(3000, "Invalid Account Number");
        }

        List<Transaction> transactions = accountService.getTransactions(accountNumber);

        log.info(
                "Returned {} transactions for the account number {}.",
                transactions != null ? transactions.size() : 0, accountNumber);

        return ResponseEntity.ok(transactions);

    }

}
