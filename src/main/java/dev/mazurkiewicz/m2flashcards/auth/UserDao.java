package dev.mazurkiewicz.m2flashcards.auth;

import java.util.Optional;

public interface UserDao {

    Optional<User> selectUserByUsername(String username);

}
