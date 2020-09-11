package dev.mazurkiewicz.m2flashcards.repository;

import dev.mazurkiewicz.m2flashcards.flashcard.entity.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFlashcardRepository extends JpaRepository<Flashcard, Long> {
}
