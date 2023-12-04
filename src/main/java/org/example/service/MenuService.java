package org.example.service;

import org.example.enums.CreditCards;
import org.example.enums.MenuMessages;
import org.example.model.Account;
import org.example.model.Game;
import org.example.model.User;
import org.example.repository.AccountRepositoryImpl;
import org.example.repository.GameRepositoryImpl;
import org.example.repository.UserRepositoryImpl;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class MenuService {

    private final UserService userService;
    private final GameService gameService;
    private final AccountService accountService;
    private final Scanner scanner;
    private User user;
    private Account account;

    public MenuService(Connection connection, Scanner scanner) {
        userService = new UserService(new UserRepositoryImpl(connection));
        gameService = new GameService(new GameRepositoryImpl(connection));
        accountService = new AccountService(new AccountRepositoryImpl(connection));
        this.scanner = scanner;
    }

    public void loging() {
        System.out.println(MenuMessages.LOGIN);
        String login = scanner.nextLine();

        System.out.println(MenuMessages.PASSWORD);
        String password = scanner.nextLine();

        user = userService.get(login, password);
        if (user != null) {
            account = accountService.get(user.getAccountId());
            getUserMenu();
        } else {
            System.out.println(MenuMessages.GREETINGS);
        }
    }

    public void register() {
        System.out.println(MenuMessages.ENTER_USER_NAME);
        String name = scanner.nextLine();

        System.out.println(MenuMessages.LOGIN);
        String nickName = scanner.nextLine();

        System.out.println(MenuMessages.PASSWORD);
        String password = scanner.nextLine();

        System.out.println(MenuMessages.ENTER_USER_BIRTHDAY);
        Date birthday = Date.valueOf(scanner.nextLine());

        System.out.println(MenuMessages.ENTER_USER_MONEY);
        float money = Float.parseFloat(scanner.nextLine());

        System.out.println(MenuMessages.ENTER_USER_TYPE_CREDITCARD);
        CreditCards typeOfCard = CreditCards.valueOf(scanner.nextLine());

        Account newAccount = Account.builder().amount(money).type(typeOfCard).build();
        Account newUserAccount = accountService.create(newAccount);
        int newUserAccountId = newUserAccount.getId();

        User newUser = User.builder()
                .accountId(newUserAccountId)
                .name(name)
                .nickname(nickName)
                .password(password)
                .birthday(birthday)
                .build();
        userService.create(newUser);

        System.out.println(MenuMessages.USER_CREATE_SUCCESSFULLY);
        System.out.println(MenuMessages.GREETINGS);
    }

    private void getUserMenu() {
        System.out.println(MenuMessages.GREETINGS_TO_USER);
        String userChoose = "0";
        while (!userChoose.equals("4")) {
            userChoose = scanner.nextLine();
            switch (userChoose) {
                case "1" -> getAllGames();
                case "2" -> buyGame();
                case "3" -> topUpAccount();
                case "4" -> System.out.println(MenuMessages.GREETINGS);
                default -> System.out.println(MenuMessages.NOT_CORRECT_INPUT);
            }
        }
    }

    private void getAllGames() {
        List<Game> allGames = gameService.getAll();
        for (Game game : allGames) {
            System.out.println(game.toString());
        }
        System.out.println(MenuMessages.GREETINGS_TO_USER);
    }

    private void buyGame() {
        List<Game> allGames = gameService.getAll();
        List<String> gamesIDs = allGames.stream()
                .map(g -> String.valueOf(g.getId()))
                .toList();

        System.out.println(MenuMessages.INPUT_GAME_ID);
        String gameIdFromUser = scanner.nextLine();

        while (!gamesIDs.contains(gameIdFromUser)) {
            System.out.println(MenuMessages.NOT_CORRECT_GAME_ID);
            gameIdFromUser = scanner.nextLine();
        }

        account = accountService.get(user.getAccountId());
        float userMoney = account.getAmount();
        Game wantedGame = gameService.get(Integer.parseInt(gameIdFromUser));
        if (userMoney > wantedGame.getCost()) {
            account.setAmount(account.getAmount() - wantedGame.getCost());
            accountService.update(account);

            userService.addGame(user, wantedGame);
            System.out.println(MenuMessages.GAME_ADDED_SUCCESSFULLY);
        } else {
            System.out.println(MenuMessages.MONEY_IS_TIGHT);
        }
        System.out.println(MenuMessages.GREETINGS_TO_USER);
    }

    private void topUpAccount() {
        System.out.println(MenuMessages.WANTED_AMOUNT_TO_INC);
        try {
            float wantedMoney = Float.parseFloat(scanner.nextLine());
            account.setAmount(account.getAmount() + wantedMoney);
            accountService.update(account);
            System.out.println(MenuMessages.ACCOUNT_INC_SUCCESSFULLY);
        } catch (NumberFormatException e) {
            System.out.println(MenuMessages.NOT_CORRECT_NUMBER_INPUT);
        }
        System.out.println(MenuMessages.GREETINGS_TO_USER);
    }
}
