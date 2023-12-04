package org.example.repository.mock;

import org.example.model.Game;
import org.example.model.User;
import org.example.repository.dao.UserRepository;

public class UserRepositoryMock implements UserRepository {

    @Override
    public User create(User user) {
        return user;
    }

    @Override
    public User getByNicknameAndPassword(String nickname, String password) {
        return User.builder().nickname(nickname).password(password).build();
    }

    @Override
    public boolean updateGames(User user, Game game) {
        return true;
    }
}
