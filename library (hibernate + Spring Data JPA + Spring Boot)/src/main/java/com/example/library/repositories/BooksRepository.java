package com.example.library.repositories;

import com.example.library.models.Book;
import com.example.library.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findByOwner(Person owner);

    List<Book> findByNameContainingIgnoreCase(String searchString);
}
