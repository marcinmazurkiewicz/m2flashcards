package dev.mazurkiewicz.m2flashcards.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class NewUserRequest {

    @NotEmpty
    @Email
    private final String email;
    @NotEmpty
    private final String password;
    @NotEmpty
    private final String confirmPassword;


}
