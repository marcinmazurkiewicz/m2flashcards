package dev.mazurkiewicz.m2flashcards.auth;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.stream.Collectors;


import static dev.mazurkiewicz.m2flashcards.auth.UserPermission.*;

public enum UserRole {
    ADMIN(Sets.newHashSet(FLASHCARD_READ, FLASHCARD_WRITE)),
    USER(Sets.newHashSet(FLASHCARD_READ)),
    AUTHOR(Sets.newHashSet(FLASHCARD_READ, FLASHCARD_WRITE));


    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<Authority> getGrantedAuthorities() {
        Set<Authority> permissions = getPermissions().stream()
                .map(permission -> new Authority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new Authority("ROLE_" + this.name()));
        return permissions;
    }
}
