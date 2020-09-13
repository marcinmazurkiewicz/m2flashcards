package dev.mazurkiewicz.m2flashcards.user;

import dev.mazurkiewicz.m2flashcards.auth.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User mapRequestToEntity(NewUserRequest userRequest) {
        User result = new User();
        result.setEmail(userRequest.getEmail());
        result.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        result.setEnabled(true);
        result.setAuthorities(UserRole.USER.getGrantedAuthorities());
        return result;
    }

    public UserResponse mapEntityToResponse(User entity) {
        return new UserResponse(entity.getId(), entity.getEmail(), entity.getAuthorities());
    }
}
