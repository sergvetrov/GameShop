package org.example.repository;

import org.example.configuration.H2Connector;
import org.example.enums.CreditCards;
import org.example.model.Account;
import org.example.model.Game;
import org.example.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

public class UserRepositoryImplTest {

    private Connection connection;
    private UserRepositoryImpl userRepository;

    @Before
    public void init() throws SQLException, IOException, ClassNotFoundException {
        connection = H2Connector.get();
        userRepository = new UserRepositoryImpl(connection);
    }

    @After
    public void closeCon() throws SQLException {
        connection.close();
    }

    @Test
    public void createTest() throws SQLException {
        User user = makeUserWithAcc();
        User actual = userRepository.create(user);

        User expected = userRepository.getByNicknameAndPassword(user.getNickname(), user.getPassword());

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getByNicknameAndPasswordTest() throws SQLException {
        User user = makeUserWithAcc();
        User expected = userRepository.create(user);

        User actual = userRepository.getByNicknameAndPassword(user.getNickname(), user.getPassword());

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateGamesTest() throws SQLException {
        User user = makeUserWithAcc();
        User userWithId = userRepository.create(user);
        Game game = Game.builder()
                .cost(22)
                .name("Test game")
                .description("testing")
                .rating(77)
                .releaseDate(Date.valueOf("1994-01-24"))
                .build();
        Game gameWithId = makeGame(game);

        userRepository.updateGames(userWithId, gameWithId);

        int userGameID = getUserGameID(userWithId);
        Assert.assertEquals(game.getId(), userGameID);
    }

    private User makeUserWithAcc() throws SQLException {
        String save =
                """
                            INSERT INTO public.accounts
                            (amount, type)
                            VALUES (?, ?)
                        """;
        Account account = Account.builder().type(CreditCards.VISA).amount(223).build();
        PreparedStatement preparedStatement = connection.prepareStatement(save, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setFloat(1, account.getAmount());
        preparedStatement.setString(2, account.getType().toString());
        preparedStatement.execute();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        account.setId(generatedKeys.getInt(1));

        return User.builder()
                .name("Oleh")
                .nickname("Oves")
                .password("makaka")
                .birthday(Date.valueOf("1982-11-27"))
                .accountId(account.getId())
                .build();
    }

    private Game makeGame(Game game) throws SQLException {
        String save = """
                INSERT INTO public.games
                (cost, name, description, rating, release_date)
                VALUES (?, ?, ?, ?, ?)""";
        PreparedStatement preparedStatement = connection.prepareStatement(save, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setFloat(1, game.getCost());
        preparedStatement.setString(2, game.getName());
        preparedStatement.setString(3, game.getDescription());
        preparedStatement.setInt(4, game.getRating());
        preparedStatement.setDate(5, game.getReleaseDate());
        preparedStatement.execute();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        game.setId(generatedKeys.getInt(1));

        return game;
    }

    private int getUserGameID(User user) throws SQLException {
        String getUserGame = """
                SELECT game_id FROM public.users_games WHERE user_id = ?""";
        PreparedStatement preparedStatement = connection.prepareStatement(getUserGame);
        preparedStatement.setInt(1, user.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("game_id");
    }
}