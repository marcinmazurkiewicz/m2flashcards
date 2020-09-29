package dev.mazurkiewicz.m2flashcards.user.validation;

import dev.mazurkiewicz.m2flashcards.user.JpaUserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueMailValidator implements ConstraintValidator<UniqueMail, String> {

    private final JpaUserRepository userRepository;

    public UniqueMailValidator(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniqueMail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String mail, ConstraintValidatorContext context) {
        return userRepository.selectUserByUsername(mail).isPresent();
    }
}
