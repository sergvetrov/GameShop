package org.example.repository;

import org.example.configuration.H2Connector;
import org.example.model.Game;
import org.example.repository.dao.GameRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameRepositoryImplTest {

    private Connection connection;
    private GameRepository gameRepository;

    @Before
    public void init() throws SQLException, IOException, ClassNotFoundException {
        connection = H2Connector.get();
        gameRepository = new GameRepositoryImpl(connection);
    }

    @After
    public void closeCon() throws SQLException {
        connection.close();
    }

    @Test
    public void getTest() throws SQLException {
        Game game = Game.builder()
                .cost(22)
                .name("Test game")
                .description("testing")
                .rating(77)
                .releaseDate(Date.valueOf("1994-01-24"))
                .build();
        Game expected = saveToDb(game);

        Game actual = gameRepository.get(game.getId());

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllTest() throws SQLException {
        List<Game> gamesToSave = List.of(Game.builder().cost(22).name("Test game").description("testing").rating(77).releaseDate(Date.valueOf("1994-01-24")).build(),
                Game.builder().cost(33).name("Test game2").description("testing2").rating(177).releaseDate(Date.valueOf("1984-01-14")).build());
        List<Game> expected = new ArrayList<>();
        for (Game game : gamesToSave) {
            expected.add(saveToDb(game));
        }

        List<Game> actual = gameRepository.getAll();

        Assert.assertEquals(expected, actual);
    }

    private Game saveToDb(Game game) throws SQLException {
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
}