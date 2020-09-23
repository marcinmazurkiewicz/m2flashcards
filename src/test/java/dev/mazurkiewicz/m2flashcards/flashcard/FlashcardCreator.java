package dev.mazurkiewicz.m2flashcards.flashcard;

import java.util.ArrayList;
import java.util.List;

public class FlashcardCreator {

    static Flashcard prepareEntity(Long id, Long author, boolean privy) {
        Flashcard entity = new Flashcard();
        entity.setId(id);
        entity.setAuthorId(author);
        entity.setQuestion("question1");
        entity.setAnswer("answer1");
        entity.setTwoSided(true);
        entity.setPrivate(privy);
        return entity;
    }

    static FlashcardResponse prepareResponse(Long id, Long author, boolean privy) {
        FlashcardResponse response = new FlashcardResponse();
        response.setId(id);
        response.setAuthorId(author);
        response.setQuestion("question1");
        response.setAnswer("answer1");
        response.setTwoSided(true);
        response.setPrivy(privy);
        return response;
    }

    static FlashcardRequest prepareRequest(boolean privy) {
        FlashcardRequest request = new FlashcardRequest();
        request.setQuestion("question1");
        request.setAnswer("answer1");
        request.setTwoSided(true);
        request.setPrivy(privy);
        return request;
    }

    static List<Flashcard> prepareFlashcardListToDb() {
        List<Flashcard> result = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Flashcard flashcard = new Flashcard();
            flashcard.setQuestion("question"+i);
            flashcard.setAnswer("answer"+i);
            flashcard.setAuthorId(100L + i%2);
            result.add(flashcard);
        }
        return result;
    }

    static List<Flashcard> prepareAuthorFlashcardList(long author) {
        List<Flashcard> result = new ArrayList<>();
        for (long i = 1L; i < 11L; i++) {
            Flashcard flashcard = new Flashcard();
            flashcard.setId(i);
            flashcard.setQuestion("question"+i);
            flashcard.setAnswer("answer"+i);
            flashcard.setAuthorId(author);
            flashcard.setPrivate(i%2==0);
            result.add(flashcard);
        }
        return result;
    }

}
