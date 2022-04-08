package com.anz.shyalika.account.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.anz.shyalika.account.util.AccountType;
import com.anz.shyalika.account.util.Currency;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the account database table.
 * 
 * @author Shyalika Benthotage
 */
@Entity(name = "account")
@Getter @Setter @NoArgsConstructor
public class AccountEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "account_number")
    private BigInteger accountNumber;

    @Column(name = "account_name")
    private String accountName;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "balance_date")
    private Date balanceDate;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "opening_available_balance")
    private BigDecimal openingAvailableBalance;

    // bi-directional many-to-one association to Transaction
    @OneToMany(mappedBy = "account")
    private List<TransactionEntity> transactions;


    /**
     * Adds a {@link TransactionEntity} object to the account's transaction list
     * 
     * @param transaction
     * @return the same transaction
     */
    public TransactionEntity addTransaction(TransactionEntity transaction) {
        this.transactions.add(transaction);
        transaction.setAccount(this);

        return transaction;
    }

    /**
     * Removes the transaction from the transaction list
     * 
     * @param transaction
     *            - transaction to be removed
     * @return null
     */
    public TransactionEntity removeTransaction(TransactionEntity transaction) {
        this.transactions.remove(transaction);
        transaction.setAccount(null);

        return transaction;
    }

}
