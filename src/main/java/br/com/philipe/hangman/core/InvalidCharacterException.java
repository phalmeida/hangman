package br.com.philipe.hangman.core;

public class InvalidCharacterException extends Exception {

    public InvalidCharacterException(String message) {
        super(message);
    }
}
