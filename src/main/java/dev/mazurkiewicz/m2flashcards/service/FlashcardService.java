package dev.mazurkiewicz.m2flashcards.service;

import dev.mazurkiewicz.m2flashcards.flashcard.entity.Flashcard;
import dev.mazurkiewicz.m2flashcards.repository.IFlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlashcardService {

    private final IFlashcardRepository repository;

    @Autowired
    public FlashcardService(IFlashcardRepository repository) {
        this.repository = repository;
    }

    public Flashcard addFlashcard(Flashcard flashcard) {
        return repository.save(flashcard);
    }

    public Flashcard findFlashcard(Long id) {
        return repository.findById(id).get();
    }
}
