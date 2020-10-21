package dev.mazurkiewicz.m2flashcards.tag;

import dev.mazurkiewicz.m2flashcards.deck.Deck;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name="tags")
public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String tagName;
    @ManyToMany(mappedBy = "tags")
    private List<Deck> decks;

}
