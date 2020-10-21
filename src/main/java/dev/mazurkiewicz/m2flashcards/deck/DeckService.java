package dev.mazurkiewicz.m2flashcards.deck;

import dev.mazurkiewicz.m2flashcards.auth.UserAuthHelper;
import dev.mazurkiewicz.m2flashcards.exception.ResourceNotFoundException;
import dev.mazurkiewicz.m2flashcards.tag.TagConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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


    public DeckResponse getDeckById(Long id) {
        Deck deck = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Deck with id %d is not found", id)));
        DeckResponse deckResponse = mapper.mapEntityToResponse(deck);
        deckResponse.setTags(tagConverter.mapToResponse(deck.getTags()));
        return deckResponse;
    }

    public List<DeckResponse> getDecksByAuthor(Long authorId) {
        List<Deck> foundDecks = repository.findByAuthorId(authorId);
        boolean isAuthorLogged = authorId.equals(userAuthHelper.geLoggedUserId());
        return isAuthorLogged
                ? foundDecks.stream()
                .map(mapper::mapEntityToResponse)
                .collect(Collectors.toList())
                : foundDecks.stream()
                .filter(deck -> !deck.isPrivate())
                .map(mapper::mapEntityToResponse)
                .collect(Collectors.toList());

    }
}
