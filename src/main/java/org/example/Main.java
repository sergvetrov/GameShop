package org.example;

import org.example.configuration.ConnectionSingleton;
import org.example.enums.MenuMessages;
import org.example.enums.RepoSystemMessages;
import org.example.service.MenuService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = ConnectionSingleton.getConnection();
        MenuService menuService = new MenuService(connection, scanner);

        System.out.println(MenuMessages.GREETINGS);
        String userInput = "init";
        while (!userInput.equals("3")) {
            userInput = scanner.nextLine();
            switch (userInput) {
                case "1" -> menuService.loging();
                case "2" -> menuService.register();
                case "3" -> System.out.println("Applications is closed");
                default -> System.out.println(MenuMessages.NOT_CORRECT_INPUT);
            }
        }
        try {
            connection.close();
            scanner.close();
        } catch (SQLException e) {
            System.out.println(RepoSystemMessages.CANT_CLOSE_CONNECTION);
        }
    }
}