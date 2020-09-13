package dev.mazurkiewicz.m2flashcards.flashcard;

import dev.mazurkiewicz.m2flashcards.flashcard.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFlashcardRepository extends JpaRepository<Flashcard, Long> {
}
