package org.example.service;

import org.example.enums.CreditCards;
import org.example.model.Account;
import org.example.repository.mock.AccountRepositoryMock;
import org.junit.Assert;
import org.junit.Test;

public class AccountServiceTest {
    private final AccountService accountService = new AccountService(new AccountRepositoryMock());

    @Test
    public void createTest() {
        Account expected = Account.builder().amount(222f).id(23).type(CreditCards.VISA).build();

        Account actual = accountService.create(expected);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateTest() {
        int expected = 1;

        int result = accountService.update(Account.builder().build());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void getTest() {
        int expected = 43;

        Account actual = accountService.get(43);

        Assert.assertEquals(expected, actual.getId());
    }
}