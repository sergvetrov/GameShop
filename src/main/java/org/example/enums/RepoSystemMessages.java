package org.example.enums;

public enum RepoSystemMessages {
    CANT_CLOSE_CONNECTION("Cannot to close connection"),
    CANT_CREATE_ACCOUNT("Cannot to create bank account"),
    CANT_UPDATE_ACCOUNT("Cannot to update bank account"),
    CANT_GET_GAME("Cannot to get game from data base"),
    CANT_CREATE_USER("Cannot to create user"),
    USER_NOT_FOUND("User not found"),
    CANT_UPDATE_USERS_GAMES("Cannot to update users games"),
    CANT_CREATE_CONNECTION("Cannot to create connection with data base"),
    CANT_GET_ACCOUNT("Cannot to get account from data base");

    private final String message;

    RepoSystemMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
