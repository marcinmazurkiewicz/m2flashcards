package dev.mazurkiewicz.m2flashcards.flashcard;

import dev.mazurkiewicz.m2flashcards.deck.Deck;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="flashcards")
public class Flashcard {

    @Id
    @GeneratedValue
    private Long id;
    private String question;
    private String answer;
    private boolean twoSided;
    private Long authorId;
    private boolean isPrivate;
    @ManyToOne
    @JoinColumn(name="deck_id")
    private Deck deck;

}
