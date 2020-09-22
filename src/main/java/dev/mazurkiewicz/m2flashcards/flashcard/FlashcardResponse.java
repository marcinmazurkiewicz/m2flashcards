package dev.mazurkiewicz.m2flashcards.flashcard;

import lombok.Data;

@Data
public class FlashcardResponse {

    private Long id;
    private String question;
    private String answer;
    private Long authorId;
    private boolean twoSided;
    private boolean privy;
}
