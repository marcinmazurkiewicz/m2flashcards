package dev.mazurkiewicz.m2flashcards.flashcard;


import org.junit.jupiter.api.Test;

import static dev.mazurkiewicz.m2flashcards.flashcard.FlashcardCreator.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FlashcardMapperTest {

    @Test
    public void whenFlashcardRequest_thenReturnFlashcard() {
        //given
        long flashcardId = 1L;
        long authorId = 100L;
        boolean privy = true;
        FlashcardMapper mapper = new FlashcardMapper();
        FlashcardRequest request = prepareRequest(privy);
        Flashcard expected = prepareEntity(flashcardId, authorId, privy);
        //when
        Flashcard result = mapper.mapRequestToEntity(request);

        //then
        assertThat(result.getQuestion()).isEqualTo(expected.getQuestion());
        assertThat(result.getAnswer()).isEqualTo(expected.getAnswer());
        assertThat(result.isPrivate()).isEqualTo(expected.isPrivate());
        assertThat(result.isTwoSided()).isEqualTo(expected.isTwoSided());
    }

    @Test
    public void whenFlashcardEntity_thenReturnFlashcardResponse() {
        //given
        long flashcardId = 1L;
        long authorId = 100L;
        boolean privy = true;

        FlashcardMapper mapper = new FlashcardMapper();
        Flashcard entity = prepareEntity(flashcardId, authorId, privy);
        FlashcardResponse expected = prepareResponse(flashcardId, authorId, privy);
        //when
        FlashcardResponse result = mapper.mapEntityToResponse(entity);

        //then
        assertThat(result.getQuestion()).isEqualTo(expected.getQuestion());
        assertThat(result.getAnswer()).isEqualTo(expected.getAnswer());
        assertThat(result.isPrivy()).isEqualTo(expected.isPrivy());
        assertThat(result.isTwoSided()).isEqualTo(expected.isTwoSided());
        assertThat(result.getAuthorId()).isEqualTo(expected.getAuthorId());
        assertThat(result.getId()).isEqualTo(expected.getId());
    }

}
