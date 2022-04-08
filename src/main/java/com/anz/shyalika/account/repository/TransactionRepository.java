package com.anz.shyalika.account.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anz.shyalika.account.entity.TransactionEntity;

/**
 * Spring Jpa repository for the {@link TransactionEntity} class
 * 
 * @author Shyalika Benthotage
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {

    /**
     * Returns a list of transactions belong to the given account number
     * 
     * @param accountNumber
     *            - unique account number
     * @return {@link List} of transations
     */
    public List<TransactionEntity> findTransactionByAccount_AccountNumber(BigInteger accountNumber);
}
