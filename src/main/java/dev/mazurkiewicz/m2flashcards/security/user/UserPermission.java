package dev.mazurkiewicz.m2flashcards.security.user;

public enum UserPermission {
    FLASHCARD_READ("flashcard:read"),
    FLASHCARD_WRITE("flashcard:write");


    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
