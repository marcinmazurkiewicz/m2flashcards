package dev.mazurkiewicz.m2flashcards.deck;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDeckRepository extends JpaRepository<Deck, Long> {

    List<Deck> findByAuthorId(Long authorId);

}
