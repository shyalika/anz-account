package com.anz.shyalika.account.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.anz.shyalika.account.util.TransactionType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the transaction database table.
 * 
 * @author Shyalika Benthotage
 */
@Entity(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String uuid;

    @Column(name = "credit_amount")
    private BigDecimal creditAmount;

    @Column(name = "debit_amount")
    private BigDecimal debitAmount;

    @Column(name = "transaction_narrative")
    private String transactionNarrative;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "value_date")
    private Date valueDate;

    // bi-directional many-to-one association to Account
    @ManyToOne
    @JoinColumn(name = "account_number", referencedColumnName = "account_number")
    private AccountEntity account;

}
