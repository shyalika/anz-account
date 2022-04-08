package com.anz.shyalika.account.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anz.shyalika.account.entity.AccountEntity;

/**
 * Spring Jpa Repository for the Account entity.
 * 
 * @author Shyalika Benthotage
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, BigInteger> {

}
