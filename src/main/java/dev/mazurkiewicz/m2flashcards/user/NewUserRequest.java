package dev.mazurkiewicz.m2flashcards.user;

import dev.mazurkiewicz.m2flashcards.user.validation.FieldMatch;
import dev.mazurkiewicz.m2flashcards.user.validation.UniqueMail;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@FieldMatch(first = "password", second = "confirmPassword")
public class NewUserRequest {

    @NotEmpty
    @Email
    @UniqueMail
    private final String email;
    @NotEmpty
    private final String password;
    @NotEmpty
    private final String confirmPassword;

}
