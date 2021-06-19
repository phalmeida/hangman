package br.com.philipe.hangman;

import br.com.philipe.hangman.game.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start(args);
    }
}
