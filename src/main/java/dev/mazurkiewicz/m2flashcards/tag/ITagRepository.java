package dev.mazurkiewicz.m2flashcards.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByTagName(String name);
}
