package dev.mazurkiewicz.m2flashcards.flashcard.controler;

import dev.mazurkiewicz.m2flashcards.flashcard.entity.Flashcard;
import dev.mazurkiewicz.m2flashcards.flashcard.service.FlashcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/flashcards")
public class FlashcardController {

    private final FlashcardService service;

    @Autowired
    public FlashcardController(FlashcardService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> addFlashcard(@RequestBody Flashcard flashcard) {
        Flashcard result = service.addFlashcard(flashcard);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public Flashcard getFlashcardById(@PathVariable Long id, Principal principal) {
        return service.findFlashcard(id);
    }
}
