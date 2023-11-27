package org.example.repository.dao;

import org.example.model.Game;
import org.example.model.User;

public interface UserRepository {

    User create(User user);

    User getByNicknameAndPassword(String nickname, String password);

    boolean updateGames(User user, Game game);
}
