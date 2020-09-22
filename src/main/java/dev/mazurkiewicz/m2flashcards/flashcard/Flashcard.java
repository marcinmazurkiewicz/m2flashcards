package dev.mazurkiewicz.m2flashcards.flashcard;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name="flashcards")
public class Flashcard {

    @Id
    @GeneratedValue
    private Long id;
    private String question;
    private String answer;
    private boolean twoSided;
    private Long authorId;
    private boolean isPrivate;

}
