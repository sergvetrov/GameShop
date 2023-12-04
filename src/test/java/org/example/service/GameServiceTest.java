package org.example.service;

import org.example.model.Game;
import org.example.repository.mock.GameRepositoryMock;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GameServiceTest {
    private final GameService gameService = new GameService(
            new GameRepositoryMock(List.of(
                    Game.builder().build(),
                    Game.builder().build(),
                    Game.builder().build())));

    @Test
    public void getTest() {
        Game game = gameService.get(43);

        int expectedId = 43;

        Assert.assertEquals(expectedId, game.getId());
    }

    @Test
    public void getAllTest() {
        List<Game> allGames = gameService.getAll();

        int expectedGames = 3;
        int currentGames = allGames.size();

        Assert.assertEquals(expectedGames, currentGames);
    }
}