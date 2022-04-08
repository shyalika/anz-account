package com.anz.shyalika.account.resource.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anz.shyalika.account.model.Account;
import com.anz.shyalika.account.service.AccountService;

import lombok.extern.slf4j.Slf4j;

/**
 * Rest controller for the Account resource. Provides Get methods for the resource only.
 * 
 * @author Shyalika Benthotage
 */
@RestController
@RequestMapping(
        path = "/accounts",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = {
            RequestMethod.GET
        })
@Slf4j
public class AccountResource {

    @Autowired
    private AccountService accountService;

    /**
     * GET method that returns a list of accounts
     */
    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() throws Exception {
        log.info("Received request for getAccounts");

        List<Account> accounts = accountService.getAccounts();

        log.info("Returned {} accounts.", accounts != null ? accounts.size() : 0);
        return ResponseEntity.ok(accounts);

    }

}
