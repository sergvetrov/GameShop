package org.example.repository;

import org.example.enums.CreditCards;
import org.example.enums.RepoSystemMessages;
import org.example.model.Account;
import org.example.repository.dao.AccountRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepositoryImpl implements AccountRepository {

    private final Connection connection;
    private static final String save =
            """
                        INSERT INTO public.accounts
                        (amount, type)
                        VALUES (?, ?)
                    """;
    private static final String update =
            """
                UPDATE public.accounts
                SET amount=?, type=?
                WHERE id = ?
            """;
    private static final String get =
            """
                SELECT * FROM public.accounts
                WHERE id = ?
            """;

    public AccountRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Account create(Account account) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(save, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setFloat(1, account.getAmount());
            preparedStatement.setString(2, account.getType().toString());
            preparedStatement.execute();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            account.setId(generatedKeys.getInt(1));

            return account;
        } catch (SQLException e) {
            System.out.println(RepoSystemMessages.CANT_CREATE_ACCOUNT);
        }
        return Account.builder().build();
    }

    @Override
    public int update(Account account) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setFloat(1, account.getAmount());
            preparedStatement.setString(2, account.getType().toString());
            preparedStatement.setInt(3, account.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(RepoSystemMessages.CANT_UPDATE_ACCOUNT);
        }
        return -1;
    }

    @Override
    public Account get(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(get);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return Account.builder()
                    .id(resultSet.getInt("id"))
                    .amount(resultSet.getFloat("amount"))
                    .type(CreditCards.valueOf(resultSet.getString("type").toUpperCase()))
                    .build();
        } catch (SQLException e) {
            System.out.println(RepoSystemMessages.CANT_GET_ACCOUNT);
        }
        return Account.builder().build();
    }
}
