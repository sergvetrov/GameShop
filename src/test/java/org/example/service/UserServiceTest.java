package org.example.service;

import org.example.model.Game;
import org.example.model.User;
import org.example.repository.mock.UserRepositoryMock;
import org.junit.Assert;
import org.junit.Test;

public class UserServiceTest {
    private final UserService userService = new UserService(new UserRepositoryMock());

    @Test
    public void createTest() {
        User expected = User.builder().id(22).password("test").nickname("Olaf").build();

        User actual = userService.create(expected);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTest() {
        User expected = User.builder().nickname("kolo").password("oko").build();

        User actual = userService.get(expected.getNickname(), expected.getPassword());

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void addGameTest() {
        Assert.assertTrue(userService.addGame(User.builder().build(), Game.builder().build()));
    }
}