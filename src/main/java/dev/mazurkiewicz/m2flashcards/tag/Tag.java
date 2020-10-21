package dev.mazurkiewicz.m2flashcards.tag;

import dev.mazurkiewicz.m2flashcards.deck.Deck;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Entity(name="tags")
public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    private String tagName;
    @ManyToMany(mappedBy = "tags")
    private List<Deck> decks;

    public Tag() {
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }
}
