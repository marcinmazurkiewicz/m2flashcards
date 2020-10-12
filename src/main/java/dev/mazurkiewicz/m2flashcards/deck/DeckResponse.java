package dev.mazurkiewicz.m2flashcards.deck;

import dev.mazurkiewicz.m2flashcards.flashcard.Flashcard;
import dev.mazurkiewicz.m2flashcards.tag.Tag;
import lombok.Data;

import java.util.List;

@Data
public class DeckResponse {

    private Long id;
    private String name;
    private Long authorId;
    private boolean isPrivate;
    private List<Tag> tags;
    private List<Flashcard> flashcards;
}
