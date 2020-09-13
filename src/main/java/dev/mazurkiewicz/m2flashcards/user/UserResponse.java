package dev.mazurkiewicz.m2flashcards.user;

import dev.mazurkiewicz.m2flashcards.auth.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class UserResponse {

    private final long id;
    private final String email;
    private final Set<Authority> authorities;

}
