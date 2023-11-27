package org.example.service;

import org.example.model.Account;
import org.example.repository.dao.AccountRepository;

public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account create(Account account) {
        return repository.create(account);
    }

    public int update(Account account) {
        return repository.update(account);
    }

    public Account get(int id) {
        return repository.get(id);
    }
}
