package dev.mazurkiewicz.m2flashcards.flashcard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlashcardRequest {

    private String question;
    private String answer;
    private boolean twoSided;
    private boolean isPublic;
    private Long authorId;

}
