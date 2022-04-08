package com.anz.shyalika.account.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.anz.shyalika.account.entity.TransactionEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * Data transfer object for the {@link TransactionEntity} class
 * 
 * @author Shyalika Benthotage
 */
@Getter
@Setter
public class Transaction {

    private BigInteger accountNumber;

    private String accountName;

    private String valueDate;

    private String currency;

    private BigDecimal debitAmount;

    private BigDecimal creditAmount;

    private String debitCredit;

    private String transactionNarrative;

}
