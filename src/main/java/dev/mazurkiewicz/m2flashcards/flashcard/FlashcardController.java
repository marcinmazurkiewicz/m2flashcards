package dev.mazurkiewicz.m2flashcards.flashcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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

    @GetMapping("/{id}")
    public FlashcardResponse getFlashcardById(@PathVariable Long id) {
        return service.findFlashcard(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeFlashcardById(@PathVariable Long id) {
        service.removeFlashcardById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> editFlashcard(@PathVariable Long id, @RequestBody @Valid FlashcardRequest flashcard) {
        service.editFlashcard(id, flashcard);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> addFlashcard(@RequestBody @Valid FlashcardRequest flashcard) {
        FlashcardResponse savedFlashcard = service.saveFlashcard(flashcard);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedFlashcard.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/author/{authorId}")
    public List<FlashcardResponse> getFlashcardByAuthorId(@PathVariable Long authorId) {
        return service.findFlashcardByAuthor(authorId);
    }


}
