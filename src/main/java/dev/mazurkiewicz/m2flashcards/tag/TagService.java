package dev.mazurkiewicz.m2flashcards.tag;

import dev.mazurkiewicz.m2flashcards.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private final ITagRepository repository;

    public TagService(ITagRepository repository) {
        this.repository = repository;
    }

    public Tag getFromRepoOrSave(String tagName) {
        return repository.findByTagName(tagName)
                .orElse(repository.save(new Tag(tagName)));
    }
}
