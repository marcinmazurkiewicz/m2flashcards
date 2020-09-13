package dev.mazurkiewicz.m2flashcards.user;

import dev.mazurkiewicz.m2flashcards.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("jpa")
public interface JpaUserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM users u WHERE LOWER(u.email) = LOWER(:username)")
    Optional<User> selectUserByUsername(String username);

}
