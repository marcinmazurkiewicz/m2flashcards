package dev.mazurkiewicz.m2flashcards.tag;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagHelper {

    private final TagService service;


    public TagHelper(TagService service) {
        this.service = service;
    }

    public List<Tag> prepareValidTagEntities(List<Tag> tagNames) {
        return tagNames.stream()
                .map(service::getFromRepoOrSave)
                .collect(Collectors.toList());
    }


}
