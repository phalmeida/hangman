package br.com.philipe.hangman.game;

import br.com.philipe.hangman.core.Config;
import br.com.philipe.hangman.core.Dictionary;
import br.com.philipe.hangman.core.InvalidCharacterException;
import br.com.philipe.hangman.core.Word;
import br.com.philipe.hangman.ui.UI;

import java.util.HashSet;
import java.util.Set;

public class Game {

    public void start(String[] args) {

        UI.print("Bem vindo ao Jogo da Forca!");

        Dictionary dictionary = Dictionary.getInstance();
        UI.print("Dicionario usado: " + dictionary.getName());

        Word word = dictionary.nextWord();

        UI.print("A palavra tem " + word.size() + " letras ");

        Set<Character> usedChars = new HashSet<>();
        int errorCount = 0;
        if (args.length > 0){
            Config.setMaxErrors(args[0]);
        }

        int maxErrors = Integer.parseInt(Config.get("maxErrors"));
        UI.print("Voce pode errar no maximo " + maxErrors);

        while (true) {
            UI.print(word);
            UI.printNewLine();

            char c;
            try {
                c = UI.readChar("Digite uma letra:");

                if (usedChars.contains(c)) {
                    throw new InvalidCharacterException("Esta letra ja foi digitada!");
                }

                usedChars.add(c);

                if (word.hasChar(c)) {
                    UI.print("Voce acertou uma letra!");
                } else {
                    errorCount++;
                    if (errorCount < maxErrors) {
                        UI.print("Voce errou! Voce ainda pode errar " + (maxErrors - errorCount) + " vez(es)");
                    }
                }

                UI.printNewLine();

                if (word.discovered()){
                    UI.print("PARABENS! Voce acertou a palavra correta: " + word.getOriginalWord());
                    UI.print("Fim de Jogo!");
                    break;
                }

                if (errorCount == maxErrors){
                    UI.print("Voce perdeu o jogo! A palavra correta era: " + word.getOriginalWord());
                    UI.print("Fim do jogo!");
                    break;
                }

            } catch (InvalidCharacterException e) {
                UI.print("Erro: " + e.getMessage());
                UI.printNewLine();
            }
        }


    }
}
