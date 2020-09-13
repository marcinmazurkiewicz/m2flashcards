package dev.mazurkiewicz.m2flashcards.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserAuthHelper {

    public long geLoggedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> details;
        try {
            details = (Map<String, Object>) authentication.getDetails();

            Object userId = details.entrySet()
                    .stream()
                    .filter(x -> x.getKey().equals("userId"))
                    .map(Map.Entry::getValue)
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Authentication don't have details about userId"));
            return ((Number) userId).longValue();
        } catch (ClassCastException e) {
            throw new IllegalStateException(String.format("Can't read authentication details: %s", e.getMessage()));
        }
    }

}
