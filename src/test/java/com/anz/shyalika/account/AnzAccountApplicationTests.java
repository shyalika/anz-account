package com.anz.shyalika.account;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.anz.shyalika.account.resource.v1.AccountResource;
import com.anz.shyalika.account.resource.v1.TransactionResource;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AnzAccountApplicationTests {

    @Autowired
    AccountResource accountResource;

    @Autowired
    TransactionResource transactionResource;
    
    @Test
    public void contextLoads() {
        Assertions.assertThat(accountResource).isNotNull();
        Assertions.assertThat(transactionResource).isNotNull();
    }

}
