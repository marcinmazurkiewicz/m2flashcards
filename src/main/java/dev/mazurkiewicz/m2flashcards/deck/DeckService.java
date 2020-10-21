package dev.mazurkiewicz.m2flashcards.deck;

import dev.mazurkiewicz.m2flashcards.auth.UserAuthHelper;
import dev.mazurkiewicz.m2flashcards.tag.TagConverter;
import org.springframework.stereotype.Service;

@Service
public class DeckService {

    private final IDeckRepository repository;
    private final TagConverter tagConverter;
    private final DeckMapper mapper;
    private final UserAuthHelper userAuthHelper;

    public DeckService(IDeckRepository repository, TagConverter tagConverter, DeckMapper mapper, UserAuthHelper userAuthHelper) {
        this.repository = repository;
        this.tagConverter = tagConverter;
        this.mapper = mapper;
        this.userAuthHelper = userAuthHelper;
    }

    public DeckResponse saveDeck(DeckRequest deck) {
        Deck toSave = mapper.mapRequestToEntity(deck);
        toSave.setAuthorId(userAuthHelper.geLoggedUserId());
        toSave.setTags(tagConverter.convertToTags(deck.getTags()));
        Deck savedDeck = repository.save(toSave);
        return mapper.mapEntityToResponse(savedDeck);
    }


}
