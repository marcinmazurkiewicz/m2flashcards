package dev.mazurkiewicz.m2flashcards.flashcard;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FlashcardMapperTest {

    @Test
    public void whenFlashcardRequest_thenReturnFlashcard() {
        //given
        FlashcardMapper mapper = new FlashcardMapper();
        FlashcardRequest request = prepareRequest();
        Flashcard expected = prepareEntity();
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
        FlashcardMapper mapper = new FlashcardMapper();
        Flashcard entity = prepareEntity();
        FlashcardResponse expected = prepareResponse();
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

    private FlashcardRequest prepareRequest() {
        FlashcardRequest request = new FlashcardRequest();
        request.setQuestion("question1");
        request.setAnswer("answer1");
        request.setTwoSided(true);
        request.setPrivy(true);
        return request;
    }

    private Flashcard prepareEntity() {
        Flashcard entity = new Flashcard();
        entity.setId(1L);
        entity.setAuthorId(100L);
        entity.setQuestion("question1");
        entity.setAnswer("answer1");
        entity.setTwoSided(true);
        entity.setPrivate(true);
        return entity;
    }

    private FlashcardResponse prepareResponse() {
        FlashcardResponse response = new FlashcardResponse();
        response.setId(1L);
        response.setAuthorId(100L);
        response.setQuestion("question1");
        response.setAnswer("answer1");
        response.setTwoSided(true);
        response.setPrivy(true);
        return response;
    }
}
