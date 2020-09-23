package dev.mazurkiewicz.m2flashcards.flashcard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlashcardRequest {

    @NotEmpty
    private String question;
    @NotEmpty
    private String answer;
    private boolean twoSided;
    private boolean privy;
}
