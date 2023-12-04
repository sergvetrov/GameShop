package org.example.repository.mock;

import org.example.model.Account;
import org.example.repository.dao.AccountRepository;

public class AccountRepositoryMock implements AccountRepository {

    @Override
    public Account create(Account account) {
        return account;
    }

    @Override
    public int update(Account account) {
        return 1;
    }

    @Override
    public Account get(int id) {
        return Account.builder().id(id).build();
    }
}
