package dev.mazurkiewicz.m2flashcards.deck;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/decks")
public class DeckController {

    private final DeckService service;

    public DeckController(DeckService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createDeck(@RequestBody @Valid DeckRequest deck) {
        DeckResponse savedDeck = service.saveDeck(deck);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedDeck.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
