package dev.mazurkiewicz.m2flashcards.deck;

import dev.mazurkiewicz.m2flashcards.flashcard.Flashcard;
import dev.mazurkiewicz.m2flashcards.tag.TagResponse;
import lombok.Data;

import java.util.List;

@Data
public class DeckResponse {

    private Long id;
    private String name;
    private Long authorId;
    private boolean isPrivate;
    private List<TagResponse> tags;
    private List<Flashcard> flashcards;
}
