package dev.mazurkiewicz.m2flashcards.deck;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeckRequest {

    @NotEmpty
    private String name;
    private boolean isPrivate;
    private List<String> tags;

}
