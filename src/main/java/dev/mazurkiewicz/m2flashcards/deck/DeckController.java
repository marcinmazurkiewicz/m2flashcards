package dev.mazurkiewicz.m2flashcards.deck;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/decks")
public class DeckController {

    private final DeckService service;

    public DeckController(DeckService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public DeckResponse getDeckById(@PathVariable Long id) {
        return service.getDeckById(id);
    }

    @GetMapping(path = "/author/{authorId}")
    public List<DeckResponse> getDecksByAuthor(@PathVariable Long authorId) {
        return service.getDecksByAuthor(authorId);
    }

    @PostMapping
    public ResponseEntity<?> createDeck(@RequestBody @Valid DeckRequest deck) {
        DeckResponse savedDeck = service.saveDeck(deck);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedDeck.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
