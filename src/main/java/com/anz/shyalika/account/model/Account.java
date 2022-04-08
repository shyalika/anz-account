package com.anz.shyalika.account.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;

/**
 * Data transfer object for the Account entity
 * 
 * @author Shyalika Benthotage
 */
@Getter
@Setter
public class Account {

    private BigInteger accountNumber;

    private String accountName;

    private String accountType;

    private String balanceDate;

    private String currency;

    private BigDecimal openingAvailableBalance;

}
