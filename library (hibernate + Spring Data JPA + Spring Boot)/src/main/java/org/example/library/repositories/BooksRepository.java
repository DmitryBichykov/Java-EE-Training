package org.example.library.repositories;

import org.example.library.models.Book;
import org.example.library.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findByOwner(Person owner);

    List<Book> findByNameContainingIgnoreCase(String searchString);
}
