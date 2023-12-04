package org.example.service;

import org.example.model.Game;
import org.example.repository.dao.GameRepository;

import java.util.List;

public class GameService {

    private final GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public Game get(int id) {
        return repository.get(id);
    }

    public List<Game> getAll() {
        return repository.getAll();
    }
}
