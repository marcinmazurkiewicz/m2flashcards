package dev.mazurkiewicz.m2flashcards.flashcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/flashcards")
public class FlashcardController {

    private final FlashcardService service;

    @Autowired
    public FlashcardController(FlashcardService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> addFlashcard(@RequestBody FlashcardRequest flashcard) {
        Flashcard savedFlashcard = service.saveFlashcard(flashcard);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedFlashcard.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public Flashcard getFlashcardById(@PathVariable Long id) {
        return service.findFlashcard(id);
    }

    @GetMapping("/author/{authorId}")
    public List<Flashcard> getFlashcardByAuthorId(@PathVariable Long authorId) {
        return service.findFlashcardByAuthor(authorId);
    }


}
