package dev.mazurkiewicz.m2flashcards.deck;

import dev.mazurkiewicz.m2flashcards.flashcard.Flashcard;
import dev.mazurkiewicz.m2flashcards.tag.Tag;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name="decks")
public class Deck {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean isPrivate;
    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name="deck_id")},
        inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tags;
    @OneToMany(mappedBy = "deck")
    private List<Flashcard> flashcards;

}
