package dev.mazurkiewicz.m2flashcards.flashcard;

import org.springframework.stereotype.Component;

@Component
public class FlashcardMapper {

    public Flashcard mapRequestToEntity(FlashcardRequest request) {
        Flashcard result = new Flashcard();
        result.setQuestion(request.getQuestion());
        result.setAnswer(request.getAnswer());
        result.setPrivate(request.isPrivy());
        result.setTwoSided(request.isTwoSided());
        return result;
    }

    public FlashcardResponse mapEntityToResponse(Flashcard entity) {
        FlashcardResponse result = new FlashcardResponse();
        result.setId(entity.getId());
        result.setQuestion(entity.getQuestion());
        result.setAnswer(entity.getAnswer());
        result.setPrivy(entity.isPrivate());
        result.setTwoSided(entity.isTwoSided());
        result.setAuthorId(entity.getAuthorId());
        return result;
    }

}
