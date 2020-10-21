package dev.mazurkiewicz.m2flashcards.deck;

import dev.mazurkiewicz.m2flashcards.tag.TagMapper;
import org.springframework.stereotype.Component;

@Component
public class DeckMapper {

    private final TagMapper tagMapper;

    public DeckMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    public Deck mapRequestToEntity(DeckRequest request) {
        Deck result = new Deck();
        result.setName(request.getName());
        result.setTags(tagMapper.mapRequestListToEntityList(request.getTags()));
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
        result.setTags(tagMapper.mapEntityListToResponseList(entity.getTags()));
        return result;
    }
}
