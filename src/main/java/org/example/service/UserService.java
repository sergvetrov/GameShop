package org.example.service;

import org.example.model.Game;
import org.example.model.User;
import org.example.repository.dao.UserRepository;

public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        return repository.create(user);
    }

    public User get(String nickname, String password){
        return repository.getByNicknameAndPassword(nickname, password);
    }

    public boolean addGame(User user, Game game){
        return repository.updateGames(user, game);
    }
}
