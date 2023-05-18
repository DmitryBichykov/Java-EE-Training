package com.example.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(min = 1, max = 50, message = "Название должно быть от 1 до 50 символов!")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(min = 3, max = 50, message = "ФИО должно быть от 3 до 50 символов!")
    @Column(name = "author_Name")
    private String authorName;
    @Min(value = 1, message = "Год должен быть меньше 0!")
    @Column(name = "year")
    private int year;

    @Column(name = "date_of_assign")
    private LocalDate dateOfAssign;

    @Transient
    private boolean isOverdue;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    public Book() {
    }

    public Book(int id, String name, String authorName, int year, Person owner, LocalDate dateOfAssign) {
        this.id = id;
        this.name = name;
        this.authorName = authorName;
        this.year = year;
        this.owner = owner;
        this.dateOfAssign = dateOfAssign;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isOverdue() {
        return isOverdue;
    }

    public void setOverdue(boolean overdue) {
        isOverdue = overdue;
    }

    public LocalDate getDateOfAssign() {
        return dateOfAssign;
    }

    public void setDateOfAssign(LocalDate dateOfAssign) {
        this.dateOfAssign = dateOfAssign;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorName='" + authorName + '\'' +
                ", year=" + year + '\'' +
                ", dateOfAssign=" + dateOfAssign +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && year == book.year && Objects.equals(name, book.name) && Objects.equals(authorName, book.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, authorName, year);
    }
}
