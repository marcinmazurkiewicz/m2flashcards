package dev.mazurkiewicz.m2flashcards.flashcard;

import dev.mazurkiewicz.m2flashcards.auth.UserAuthHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Flashcard addFlashcard(FlashcardRequest flashcard) {
        Flashcard flashcardToSave = flashcardMapper.mapRequestToEntity(flashcard);
        flashcardToSave.setAuthorId(userAuthHelper.geLoggedUserId());
        return repository.save(flashcardToSave);
    }

    public Flashcard findFlashcard(Long id) {
        return repository.findById(id).get();
    }


}