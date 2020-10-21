package dev.mazurkiewicz.m2flashcards.deck;

import dev.mazurkiewicz.m2flashcards.auth.UserAuthHelper;
import dev.mazurkiewicz.m2flashcards.exception.ResourceNotFoundException;
import dev.mazurkiewicz.m2flashcards.tag.TagHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeckService {

    private final IDeckRepository repository;
    private final TagHelper tagHelper;
    private final DeckMapper mapper;
    private final UserAuthHelper userAuthHelper;

    public DeckService(IDeckRepository repository, TagHelper tagHelper, DeckMapper mapper, UserAuthHelper userAuthHelper) {
        this.repository = repository;
        this.tagHelper = tagHelper;
        this.mapper = mapper;
        this.userAuthHelper = userAuthHelper;
    }

    public DeckResponse saveDeck(DeckRequest deck) {
        Deck toSave = mapper.mapRequestToEntity(deck);
        toSave.setAuthorId(userAuthHelper.geLoggedUserId());
        toSave.setTags(tagHelper.prepareValidTagEntities(toSave.getTags()));
        Deck savedDeck = repository.save(toSave);
        return mapper.mapEntityToResponse(savedDeck);
    }


    public DeckResponse getDeckById(Long id) {
        return repository.findById(id)
                .map(mapper::mapEntityToResponse)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Deck with id %d is not found", id)));
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
