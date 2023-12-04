package org.example.repository;

import org.example.enums.RepoSystemMessages;
import org.example.model.Game;
import org.example.repository.dao.GameRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameRepositoryImpl implements GameRepository {

    private final Connection connection;
    private static final String get =
            """
                        SELECT * FROM public.games
                        WHERE id = ?
                    """;
    private static final String getAll =
            """
                        SELECT * FROM public.games
                    """;

    public GameRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Game get(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(get);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return createGame(resultSet);
        } catch (SQLException e) {
            System.out.println(RepoSystemMessages.CANT_GET_GAME);
        }
        return Game.builder().build();
    }

    @Override
    public List<Game> getAll() {
        List<Game> games = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                games.add(createGame(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(RepoSystemMessages.CANT_GET_GAME);
        }
        return games;
    }

    private Game createGame(ResultSet rs) throws SQLException {
        return Game.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .releaseDate(rs.getDate("release_date"))
                .rating(rs.getInt("rating"))
                .cost(rs.getFloat("cost"))
                .description(rs.getString("description"))
                .build();
    }
}
