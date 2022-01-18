package com.game.exception;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException() {
        super("Введены некорректные данные.");
    }
}
