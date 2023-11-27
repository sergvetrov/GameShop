package org.example.repository;

import org.example.enums.RepoSystemMessages;
import org.example.model.Game;
import org.example.model.User;
import org.example.repository.dao.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection;
    private static final String save =
            """
                        INSERT INTO public.users
                        (name, nickname, birthday, password, account_id)
                        VALUES (?, ?, ?, ?, ?)
                    """;
    private static final String getByNicknameAndPass =
            """
                        SELECT * FROM public.users
                        WHERE nickname = ? AND password = ?
                    """;
    private static final String updateGames =
            """
                        INSERT INTO public.users_games
                        (user_id, game_id)
                        VALUES (?, ?)
                    """;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User create(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(save, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getNickname());
            preparedStatement.setDate(3, user.getBirthday());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getAccountId());
            preparedStatement.execute();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getInt(1));

            return user;
        } catch (SQLException e) {
            System.out.println(RepoSystemMessages.CANT_CREATE_USER);
        }
        return User.builder().build();
    }

    @Override
    public User getByNicknameAndPassword(String nickname, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getByNicknameAndPass);
            preparedStatement.setString(1, nickname);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return User.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .nickname(resultSet.getString("nickname"))
                    .birthday(resultSet.getDate("birthday"))
                    .password(resultSet.getString("password"))
                    .accountId(resultSet.getInt("account_id"))
                    .build();
        } catch (SQLException e) {
            System.out.println(RepoSystemMessages.USER_NOT_FOUND);
        }
        return null;
    }

    @Override
    public boolean updateGames(User user, Game game) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateGames);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, game.getId());
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(RepoSystemMessages.CANT_UPDATE_USERS_GAMES);
        }
        return false;
    }
}
