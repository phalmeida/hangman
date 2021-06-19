package br.com.philipe.jogoforca.core;

import java.util.Arrays;
import java.util.List;

public class StaticDictionary extends Dictionary {

    private List<String> words = Arrays.asList("casa", "computador", "caneta", "carro");
    private int currentIndex = -1;

    @Override
    public Word nextWord() {
        currentIndex = (currentIndex + 1) % words.size();
        return new Word(words.get(currentIndex));
    }

    @Override
    public String getName() {
        return "Estático";
    }
}
