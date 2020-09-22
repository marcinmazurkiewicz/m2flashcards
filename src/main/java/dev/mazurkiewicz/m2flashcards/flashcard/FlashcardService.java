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

    public FlashcardResponse saveFlashcard(FlashcardRequest flashcard) {
        Flashcard flashcardToSave = flashcardMapper.mapRequestToEntity(flashcard);
        flashcardToSave.setAuthorId(userAuthHelper.geLoggedUserId());
        Flashcard savedFlashcard = repository.save(flashcardToSave);
        return flashcardMapper.mapEntityToResponse(savedFlashcard);
    }

    public FlashcardResponse editFlashcard(Long flashcardId, FlashcardRequest newFlashcard) {
        Flashcard oldFlashcard = repository.findById(flashcardId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Flashcard with id %d is not found", flashcardId)));
        long authorId = userAuthHelper.geLoggedUserId();
        if (!oldFlashcard.getAuthorId().equals(authorId)) {
            throw new UnauthorizedAccessException(String.format("You can't edit flashcard %d, because you are not author!", flashcardId));
        }

        Flashcard flashcardToSave = flashcardMapper.mapRequestToEntity(newFlashcard);
        flashcardToSave.setId(oldFlashcard.getId());
        flashcardToSave.setAuthorId(oldFlashcard.getAuthorId());
        Flashcard savedFlashcard = repository.save(flashcardToSave);

        return flashcardMapper.mapEntityToResponse(savedFlashcard);
    }

    public FlashcardResponse findFlashcard(Long id) throws HttpClientErrorException {
        Flashcard flashcard = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Flashcard with id %d is not found", id)));
        boolean privy = flashcard.isPrivate() && !flashcard.getAuthorId().equals(userAuthHelper.geLoggedUserId());
        if (privy)
            throw new UnauthorizedAccessException(String.format("Flashcard %d is private", id));

        return flashcardMapper.mapEntityToResponse(flashcard);
    }

    public List<FlashcardResponse> findFlashcardByAuthor(Long authorId) {
        List<Flashcard> foundFlashcards = repository.findFlashcardByAuthorId(authorId);
        boolean isAuthorLogged = authorId.equals(userAuthHelper.geLoggedUserId());
        return isAuthorLogged
                ? foundFlashcards.stream()
                .map(flashcardMapper::mapEntityToResponse)
                .collect(Collectors.toList())
                : foundFlashcards.stream()
                .filter(Flashcard::isPrivate)
                .map(flashcardMapper::mapEntityToResponse)
                .collect(Collectors.toList());
    }
}
