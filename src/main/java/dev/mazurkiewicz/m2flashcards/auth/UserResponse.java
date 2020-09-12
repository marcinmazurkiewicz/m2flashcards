package dev.mazurkiewicz.m2flashcards.auth;

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
