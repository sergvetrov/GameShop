package org.example.repository.dao;

import org.example.model.Account;

public interface AccountRepository {

    Account create(Account account);

    int update(Account account);

    Account get(int id);
}
