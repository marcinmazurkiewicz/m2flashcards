package dev.mazurkiewicz.m2flashcards.tag;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagRequest {

    private Long id;
    private String tagName;
}
