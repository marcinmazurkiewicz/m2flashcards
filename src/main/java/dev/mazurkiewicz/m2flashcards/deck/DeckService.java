package dev.mazurkiewicz.m2flashcards.deck;

import org.springframework.stereotype.Service;

@Service
public class DeckService {

    private final IDeckRepository repository;

    public DeckService(IDeckRepository repository) {
        this.repository = repository;
    }

    public Deck saveDeck(Deck deck) {
        return repository.save(deck);
    }
}
