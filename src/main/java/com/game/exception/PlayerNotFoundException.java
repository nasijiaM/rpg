package com.game.exception;

public class PlayerNotFoundException extends RuntimeException{


    public PlayerNotFoundException() {
        super("В базе данных нет такого игрока");
    }

}
