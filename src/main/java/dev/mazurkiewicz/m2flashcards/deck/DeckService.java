package dev.mazurkiewicz.m2flashcards.deck;

import dev.mazurkiewicz.m2flashcards.auth.UserAuthHelper;
import dev.mazurkiewicz.m2flashcards.tag.TagService;
import org.springframework.stereotype.Service;

@Service
public class DeckService {

    private final IDeckRepository repository;
    private final TagService tagService;
    private final DeckMapper mapper;
    private final UserAuthHelper userAuthHelper;

    public DeckService(IDeckRepository repository, TagService tagService, DeckMapper mapper, UserAuthHelper userAuthHelper) {
        this.repository = repository;
        this.tagService = tagService;
        this.mapper = mapper;
        this.userAuthHelper = userAuthHelper;
    }

    public DeckResponse saveDeck(DeckRequest deck) {
        Deck toSave = mapper.mapRequestToEntity(deck);
        toSave.setAuthorId(userAuthHelper.geLoggedUserId());
        Deck savedDeck = repository.save(toSave);
        return mapper.mapEntityToResponse(savedDeck);
    }
}
