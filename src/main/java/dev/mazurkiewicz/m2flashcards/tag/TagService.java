package dev.mazurkiewicz.m2flashcards.tag;

import org.springframework.stereotype.Service;

@Service
public class TagService {

    private final ITagRepository repository;

    public TagService(ITagRepository repository) {
        this.repository = repository;
    }

    public Tag getFromRepoOrSave(Tag tag) {
        return repository.findByTagName(tag.getTagName())
                .orElseGet(() -> repository.save(tag));
    }
}
