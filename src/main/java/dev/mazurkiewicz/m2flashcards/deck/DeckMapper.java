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

    public DeckResponse mapEntityToResponse(Deck entity) {
        DeckResponse result = new DeckResponse();
        result.setId(entity.getId());
        result.setAuthorId(entity.getAuthorId());
        result.setName(entity.getName());
        result.setPrivate(entity.isPrivate());
        result.setFlashcards(entity.getFlashcards());
        return result;
    }
}
