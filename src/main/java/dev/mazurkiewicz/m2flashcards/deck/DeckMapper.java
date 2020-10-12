package dev.mazurkiewicz.m2flashcards.deck;

import org.springframework.stereotype.Component;

@Component
public class DeckMapper {

    public Deck mapRequestToEntity(DeckRequest request) {
        Deck result = new Deck();
        result.setName(request.getName());
//        result.setTags(request.getTags());
        result.setPrivate(request.isPrivate());
        return result;
    }
}
