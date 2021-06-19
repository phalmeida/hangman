package br.com.philipe.hangman.core;

import br.com.philipe.hangman.game.GameException;
import br.com.philipe.hangman.utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileDictionary extends Dictionary {

    private static final String FILE_NAME = "dictionary.txt";
    private List<String> words = new ArrayList<>();
    private static FileDictionary instance;

    public FileDictionary() {
        load();
    }

    public static FileDictionary getInstance(){
        if (instance == null){
            instance = new FileDictionary();
        }
        return instance;
    }

    private void load() {
        try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/" + FILE_NAME))) {
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                words.add(word);
            }
            if (words.size() == 0) {
                throw new GameException("A lista de palavras não pode ser  vazia!");
            }
        }
    }

    @Override
    public Word nextWord(){
        int pos = RandomUtils.newRandomNumber(0, words.size());
         return new Word(words.get(pos));
    }

    @Override
    public String getName() {
        return "Arquivo " + FILE_NAME;
    }
}
