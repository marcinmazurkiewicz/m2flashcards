package dev.mazurkiewicz.m2flashcards.tag;

import org.springframework.stereotype.Service;

@Service
public class TagService {

    private final ITagRepository repository;

    public TagService(ITagRepository repository) {
        this.repository = repository;
    }
}
