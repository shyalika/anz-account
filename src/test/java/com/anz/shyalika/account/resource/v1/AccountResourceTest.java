package com.anz.shyalika.account.resource.v1;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.anz.shyalika.account.model.Account;
import com.anz.shyalika.account.service.AccountService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountResource.class)
class AccountResourceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    @Test
    void testGetAccounts_Success() throws Exception {
        Account account = new Account();
        account.setAccountNumber(new BigInteger("321143048"));
        account.setAccountName("AUCurrent433");
        account.setAccountType("Savings");
        account.setBalanceDate("08/11/2018");
        account.setCurrency("AUD");
        account.setOpeningAvailableBalance(new BigDecimal("10000.00"));
        
        Account account2 = new Account();
        Account account3 = new Account();

        List<Account> records = new ArrayList<>(
                Arrays.asList(account, account2, account3));

        Mockito.when(accountService.getAccounts()).thenReturn(records);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/accounts").contentType(
                        MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                            .andExpect(jsonPath("$", hasSize(3)))
                            .andExpect(jsonPath("$[0].accountNumber", is(account.getAccountNumber().intValue())))
                            .andExpect(jsonPath("$[0].accountName", is(account.getAccountName())))
                            .andExpect(jsonPath("$[0].accountType", is(account.getAccountType())))
                            .andExpect(jsonPath("$[0].balanceDate", is(account.getBalanceDate())))
                            .andExpect(jsonPath("$[0].currency", is(account.getCurrency())))
                            .andExpect(jsonPath("$[0].openingAvailableBalance", is(account.getOpeningAvailableBalance().doubleValue())));
    }
    
    @Test
    void testGetAccounts_Fail() throws Exception {

        Mockito.when(accountService.getAccounts()).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/accounts").contentType(
                        MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                            .andExpect(content().string(""));
    }

}
