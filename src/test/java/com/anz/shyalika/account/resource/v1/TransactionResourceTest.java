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

import org.hamcrest.core.IsNull;
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

import com.anz.shyalika.account.model.Transaction;
import com.anz.shyalika.account.service.AccountService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionResource.class)
class TransactionResourceTest {

    private static final BigInteger TEST_ACCOUNT_NUMBER = new BigInteger("321143048");

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    @Test
    void testGetTransactions_Success() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(TEST_ACCOUNT_NUMBER);
        transaction.setAccountName("AUCurrent433");
        transaction.setDebitCredit("Debit");
        transaction.setValueDate("08/11/2018");
        transaction.setCurrency("AUD");
        transaction.setDebitAmount(new BigDecimal("10000.00"));
        transaction.setTransactionNarrative("transaction narrative");
        
        Transaction transaction2 = new Transaction();
        Transaction transaction3 = new Transaction();

        List<Transaction> records = new ArrayList<>(
                Arrays.asList(transaction, transaction2, transaction3));

        
        Mockito.when(accountService.getTransactions(TEST_ACCOUNT_NUMBER)).thenReturn(records);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/transactions/321143048").contentType(
                        MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                            .andExpect(jsonPath("$", hasSize(3)))
                            .andExpect(jsonPath("$[0].accountNumber", is(transaction.getAccountNumber().intValue())))
                            .andExpect(jsonPath("$[0].accountName", is(transaction.getAccountName())))
                            .andExpect(jsonPath("$[0].debitCredit", is(transaction.getDebitCredit())))
                            .andExpect(jsonPath("$[0].valueDate", is(transaction.getValueDate())))
                            .andExpect(jsonPath("$[0].currency", is(transaction.getCurrency())))
                            .andExpect(jsonPath("$[0].debitAmount", is(transaction.getDebitAmount().doubleValue())))
                            .andExpect(jsonPath("$[0].creditAmount").value(IsNull.nullValue()));
    }
    
    @Test
    void testGetTransactions_NoRecords() throws Exception {

        Mockito.when(accountService.getTransactions(TEST_ACCOUNT_NUMBER)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/transactions/321143048").contentType(
                        MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                            .andExpect(content().string(""));
    }
    
    @Test
    void testPostTransactions() throws Exception {

        Mockito.when(accountService.getTransactions(TEST_ACCOUNT_NUMBER)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/transactions/321143048").contentType(
                        MediaType.APPLICATION_JSON)).andExpect(status().is(405))
                            .andExpect(content().string("Method not supported"));
    }
    
    @Test
    void testGetTransactions_NotSupported() throws Exception {

        Mockito.when(accountService.getTransactions(TEST_ACCOUNT_NUMBER)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/transactions/asa").contentType(
                        MediaType.APPLICATION_JSON)).andExpect(status().is(405))
                            .andExpect(content().string("Method argument type mismatched"));
    }

}
