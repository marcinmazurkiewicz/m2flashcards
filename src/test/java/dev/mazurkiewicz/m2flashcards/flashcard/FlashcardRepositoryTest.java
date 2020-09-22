package dev.mazurkiewicz.m2flashcards.flashcard;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FlashcardRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IFlashcardRepository repository;

    @Test
    public void whenFindFlashcardByAuthorId_thenReturnFlashcard() {
        //given
        List<Flashcard> flashcards = prepareFlashcardList();
        flashcards.forEach(entityManager::persist);
        entityManager.flush();
        long authorId = 100L;

        //when
        List<Flashcard> found = repository.findFlashcardByAuthorId(authorId);

        //then
        assertThat(found)
                .containsExactlyInAnyOrderElementsOf(
                        flashcards.stream()
                                .filter(flashcard -> flashcard.getAuthorId().equals(authorId))
                                .collect(Collectors.toList())
                );
    }

    @Test
    public void whenWrongIdParamInFindFlashcardByAuthorId_thenReturnEmptyList() {
        //given
        List<Flashcard> flashcards = prepareFlashcardList();
        flashcards.forEach(entityManager::persist);
        entityManager.flush();

        //when
        List<Flashcard> found = repository.findFlashcardByAuthorId(666L);

        //then
        assertThat(found).isEmpty();
    }

    @Test
    public void whenNullParamInFindFlashcardByAuthorId_thenReturnEmptyList() {
        //given
        List<Flashcard> flashcards = prepareFlashcardList();
        flashcards.forEach(entityManager::persist);
        entityManager.flush();

        //when
        List<Flashcard> found = repository.findFlashcardByAuthorId(null);

        //then
        assertThat(found).isEmpty();
    }

    private List<Flashcard> prepareFlashcardList() {
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

}
