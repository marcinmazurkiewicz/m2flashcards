package dev.mazurkiewicz.m2flashcards.flashcard;

import org.springframework.stereotype.Component;

@Component
public class FlashcardMapper {

    public Flashcard mapRequestToEntity(FlashcardRequest request) {
        Flashcard result = new Flashcard();
        result.setQuestion(request.getQuestion());
        result.setAnswer(request.getAnswer());
        result.setPublic(!request.isPrivy());
        result.setTwoSided(request.isTwoSided());
        return result;
    }

}
