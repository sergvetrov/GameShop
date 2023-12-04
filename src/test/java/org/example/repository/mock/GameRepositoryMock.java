package org.example.repository.mock;

import org.example.model.Game;
import org.example.repository.dao.GameRepository;

import java.util.List;

public class GameRepositoryMock implements GameRepository {
    private final List<Game> games;

    public GameRepositoryMock(List<Game> games) {
        this.games = games;
    }

    @Override
    public Game get(int id) {
        return Game.builder().id(id).build();
    }

    @Override
    public List<Game> getAll() {
        return games;
    }
}
