package dev.mazurkiewicz.m2flashcards.flashcard;

import dev.mazurkiewicz.m2flashcards.auth.UserAuthHelper;
import dev.mazurkiewicz.m2flashcards.exception.ResourceNotFoundException;
import dev.mazurkiewicz.m2flashcards.exception.UnauthorizedAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlashcardService {

    private final IFlashcardRepository repository;
    private final FlashcardMapper flashcardMapper;
    private final UserAuthHelper userAuthHelper;

    @Autowired
    public FlashcardService(IFlashcardRepository repository, FlashcardMapper flashcardMapper, UserAuthHelper userAuthHelper) {
        this.repository = repository;
        this.flashcardMapper = flashcardMapper;
        this.userAuthHelper = userAuthHelper;
    }

    public Flashcard saveFlashcard(FlashcardRequest flashcard) {
        Flashcard flashcardToSave = flashcardMapper.mapRequestToEntity(flashcard);
        flashcardToSave.setAuthorId(userAuthHelper.geLoggedUserId());
        return repository.save(flashcardToSave);
    }

    public Flashcard findFlashcard(Long id) throws HttpClientErrorException {
        Flashcard flashcard = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Flashcard with id %d is not found", id)));
        boolean visible = flashcard.isPublic() || flashcard.getAuthorId().equals(userAuthHelper.geLoggedUserId());
        if (!visible)
            throw new UnauthorizedAccessException(String.format("Flashcard %d is private", id));

        return flashcard;
    }

    public List<Flashcard> findFlashcardByAuthor(Long authorId) {
        List<Flashcard> foundFlashcards = repository.findFlashcardByAuthorId(authorId);
        boolean isAuthorLogged = authorId.equals(userAuthHelper.geLoggedUserId());
        return isAuthorLogged ? foundFlashcards :
                foundFlashcards.stream()
                        .filter(Flashcard::isPublic)
                        .collect(Collectors.toList());
    }
}
