package dev.mazurkiewicz.m2flashcards.security.user;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;


import static dev.mazurkiewicz.m2flashcards.security.user.UserPermission.*;

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

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
