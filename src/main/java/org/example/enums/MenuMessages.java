package org.example.enums;

public enum MenuMessages {
    GREETINGS("""
            Hello in video-game shop!
            Please, log in or register. For log in please push - 1, register push - 2, exit push - 3"""),
    LOGIN("Please enter your nickname"),
    PASSWORD("Please enter your password"),
    GREETINGS_TO_USER("[LOGGED], 1 - to see all games, 2 - to buy some game, 3 - to increase your money in bank account , 4 - back to previous menu"),
    NOT_CORRECT_INPUT("Please choose correct item"),
    INPUT_GAME_ID("Please choose game id"),
    NOT_CORRECT_GAME_ID("Not correct game ID"),
    WANTED_AMOUNT_TO_INC("Enter wanted amount of money to add to your account"),
    NOT_CORRECT_NUMBER_INPUT("Not correct, please enter numeric value"),
    GAME_ADDED_SUCCESSFULLY("Game bought successfully"),
    ACCOUNT_INC_SUCCESSFULLY("Account increased successfully"),
    MONEY_IS_TIGHT("There is not enough money in your account"),
    ENTER_USER_NAME("Please enter your name:"),
    ENTER_USER_BIRTHDAY("Please enter your birthday, format(yyyy-mm-dd):"),
    ENTER_USER_MONEY("Please enter your amount of money:"),
    ENTER_USER_TYPE_CREDITCARD("Please enter your type of credit card (VISA or MASTERCARD):"),
    USER_CREATE_SUCCESSFULLY("User created!");

    private final String message;

    MenuMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
