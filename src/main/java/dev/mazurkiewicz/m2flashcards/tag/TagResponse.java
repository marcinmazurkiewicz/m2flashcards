package dev.mazurkiewicz.m2flashcards.tag;

import lombok.Data;

@Data
public class TagResponse {

    private Long id;
    private String tagName;

    public TagResponse() {
    }

    public TagResponse(Tag tag) {
        this.id = tag.getId();
        this.tagName = tag.getTagName();
    }
}
