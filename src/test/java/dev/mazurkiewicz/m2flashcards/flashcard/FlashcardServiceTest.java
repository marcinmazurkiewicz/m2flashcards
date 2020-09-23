package dev.mazurkiewicz.m2flashcards.flashcard;

import dev.mazurkiewicz.m2flashcards.auth.UserAuthHelper;
import dev.mazurkiewicz.m2flashcards.exception.ResourceNotFoundException;
import dev.mazurkiewicz.m2flashcards.exception.UnauthorizedAccessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static dev.mazurkiewicz.m2flashcards.flashcard.FlashcardCreator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlashcardServiceTest {

    @MockBean
    private IFlashcardRepository repository;
    @MockBean
    private UserAuthHelper authHelper;

    @Autowired
    private FlashcardService service;


    @Test
    public void whenFindFlashcardIsPrivyAndUserOwned_thenFlashcardResponseShouldBeReturned() {
        //given
        long flashcardId = 1L;
        long authorId = 100L;
        boolean privy = true;
        FlashcardResponse expected = prepareResponse(flashcardId, authorId, privy);

        //when
        when(repository.findById(flashcardId)).thenReturn(Optional.of(prepareEntity(flashcardId, authorId, privy)));
        when(authHelper.geLoggedUserId()).thenReturn(authorId);
        FlashcardResponse returned = service.findFlashcard(flashcardId);

        //then
        assertThat(returned).isEqualTo(expected);
    }

    @Test
    public void whenFindFlashcardIsPublicAndUserOwned_thenFlashcardResponseShouldBeReturned() {
        //given
        long flashcardId = 1L;
        long authorId = 100L;
        boolean privy = false;
        FlashcardResponse expected = prepareResponse(flashcardId, authorId, privy);

        //when
        when(repository.findById(flashcardId)).thenReturn(Optional.of(prepareEntity(flashcardId, authorId, privy)));
        when(authHelper.geLoggedUserId()).thenReturn(authorId);
        FlashcardResponse returned = service.findFlashcard(flashcardId);

        //then
        assertThat(returned).isEqualTo(expected);
    }

    @Test
    public void whenFindFlashcardIsPublic_thenFlashcardResponseShouldBeReturned() {
        //given
        long flashcardId = 1L;
        long authorId = 100L;
        long authUserId = 101L;
        boolean privy = false;
        FlashcardResponse expected = prepareResponse(flashcardId, authorId, privy);

        //when
        when(repository.findById(flashcardId)).thenReturn(Optional.of(prepareEntity(flashcardId, authorId, privy)));
        when(authHelper.geLoggedUserId()).thenReturn(authUserId);
        FlashcardResponse returned = service.findFlashcard(flashcardId);

        //then
        assertThat(returned).isEqualTo(expected);
    }

    @Test
    public void whenFindFlashcardIsPrivy_thenUnauthorizedAccessExceptionShouldBeThrows() {
        //given
        long flashcardId = 1L;
        long authorId = 100L;
        long authUserId = 101L;
        boolean privy = true;

        //when
        when(repository.findById(flashcardId)).thenReturn(Optional.of(prepareEntity(flashcardId, authorId, privy)));
        when(authHelper.geLoggedUserId()).thenReturn(authUserId);
        Assertions.assertThrows(UnauthorizedAccessException.class, () -> service.findFlashcard(flashcardId));
    }

    @Test
    public void whenFindFlashcardThatNotExist_thenResourceNotFoundExceptionShouldBeThrows() {
        //given
        long flashcardId = 2L;
        long authorId = 100L;

        //when
        when(repository.findById(flashcardId)).thenReturn(Optional.empty());
        when(authHelper.geLoggedUserId()).thenReturn(authorId);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.findFlashcard(flashcardId));
    }

    @Test
    public void whenFindFlashcardByAuthorCallByAuthor_thenFlashcardResponseListShouldBeReturned() {
        //given
        long authorId = 100L;
        List<Flashcard> entitiesList = prepareAuthorFlashcardList(authorId);
        FlashcardMapper mapper = new FlashcardMapper();
        List<FlashcardResponse> expected = entitiesList.stream()
                .map(mapper::mapEntityToResponse)
                .collect(Collectors.toList());

        //when
        when(repository.findFlashcardByAuthorId(authorId)).thenReturn(entitiesList);
        when(authHelper.geLoggedUserId()).thenReturn(authorId);
        List<FlashcardResponse> returned = service.findFlashcardByAuthor(authorId);

        //then
        assertThat(returned).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void whenFindFlashcardByAuthorCallByOtherUser_thenPublicFlashcardResponseListShouldBeReturned() {
        //given
        long authorId = 100L;
        long authUserId = 101L;

        List<Flashcard> entitiesList = prepareAuthorFlashcardList(authorId);
        FlashcardMapper mapper = new FlashcardMapper();
        List<FlashcardResponse> expected = entitiesList.stream()
                .filter(flashcard -> !flashcard.isPrivate())
                .map(mapper::mapEntityToResponse)
                .collect(Collectors.toList());

        //when
        when(repository.findFlashcardByAuthorId(authorId)).thenReturn(entitiesList);
        when(authHelper.geLoggedUserId()).thenReturn(authUserId);
        List<FlashcardResponse> returned = service.findFlashcardByAuthor(authorId);

        //then
        assertThat(returned).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void whenFindFlashcardByAuthorWithNonExistAuthorId_thenPublicFlashcardResponseListShouldBeReturned() {
        //given
        long authorId = 666L;
        long authUserId = 100L;

        //when
        when(repository.findFlashcardByAuthorId(authorId)).thenReturn(new ArrayList<>());
        when(authHelper.geLoggedUserId()).thenReturn(authUserId);
        List<FlashcardResponse> returned = service.findFlashcardByAuthor(authorId);

        //then
        assertThat(returned).isEmpty();
    }

}
