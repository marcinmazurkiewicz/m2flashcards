package dev.mazurkiewicz.m2flashcards.tag;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagConverter {

    private final TagService service;


    public TagConverter(TagService service) {
        this.service = service;
    }

    public List<Tag> convertToTags(List<String> tagNames) {
        return tagNames.stream()
                .map(service::getFromRepoOrSave)
                .collect(Collectors.toList());
    }

    public List<TagResponse> mapToResponse(List<Tag> tags) {
        return tags.stream()
                .map(TagResponse::new)
                .collect(Collectors.toList());
    }
}
