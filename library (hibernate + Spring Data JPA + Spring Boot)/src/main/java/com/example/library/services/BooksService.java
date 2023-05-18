package com.example.library.services;

import com.example.library.models.Book;
import com.example.library.models.Person;
import com.example.library.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Object findByOwner(Person owner) {
        return booksRepository.findByOwner(owner);
    }

    public List<Book> findAll(int page, int booksPerPage, String sortField) {
        return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by(sortField))).getContent();
    }

    public List<Book> findAll(String sortField) {
        return booksRepository.findAll(Sort.by(sortField));
    }

    public List<Book> findAll(int page, int booksPerPage) {
        return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updateBook) {
        updateBook.setId(id);
        booksRepository.save(updateBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void assign(Book book, Person person) {
        book.setOwner(person);
        book.setDateOfAssign(LocalDate.now());
        booksRepository.save(book);
    }

    @Transactional
    public void release(Book book) {
        book.setOwner(null);
        book.setDateOfAssign(null);
        booksRepository.save(book);
    }

    public List<Book> searchBook(String searchString) {
        return booksRepository.findByNameContainingIgnoreCase(searchString);
    }
}
