package org.example.library.dao;

import org.example.library.models.Book;
import org.example.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Object listBook(int personId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE personId=?", new Object[]{personId}, new BeanPropertyRowMapper<>(Book.class)).
                stream().findAny().orElse(null);
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public Optional<Person> checkPersonId(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.personId=Person.personId" +
                        " WHERE Book.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, year, authorName) VALUES(?, ?, ?)", book.getName(), book.getYear(),
                book.getAuthorName());
    }

    public void update(int id, Book updateBook) {
        jdbcTemplate.update("UPDATE Book SET name=?, year=?, authorName=? WHERE id=?", updateBook.getName(),
                updateBook.getYear(), updateBook.getAuthorName(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }


    public void assign(int id, Person person) {
        jdbcTemplate.update("UPDATE Book Set personId=? WHERE id=?", person.getPersonId(), id);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET personId=? WHERE id=?", null, id);
    }
}
