package org.example.repository;

import org.example.configuration.H2Connector;
import org.example.enums.CreditCards;
import org.example.model.Account;
import org.example.repository.dao.AccountRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AccountRepositoryImplTest {

    private Connection connection;
    private AccountRepository accountRepository;
    private final Account account = Account.builder()
            .type(CreditCards.VISA)
            .amount(321)
            .build();

    @Before
    public void init() throws SQLException, IOException, ClassNotFoundException {
        connection = H2Connector.get();
        accountRepository = new AccountRepositoryImpl(connection);
    }

    @After
    public void closeCon() throws SQLException {
        connection.close();
    }

    @Test
    public void createTest() {
        Account expected = accountRepository.create(account);
        Account actual = accountRepository.get(expected.getId());

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateTest() {
        Account accWithId = accountRepository.create(account);
        accWithId.setAmount(555);

        accountRepository.update(accWithId);
        Account actual = accountRepository.get(accWithId.getId());

        Assert.assertEquals(accWithId, actual);
    }

    @Test
    public void getTest() {
        Account actual = accountRepository.create(account);
        Account expected = accountRepository.get(actual.getId());

        Assert.assertEquals(expected, actual);
    }
}