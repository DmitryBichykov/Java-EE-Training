package org.example.library.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Book {

    private int id;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(min = 1, max = 50, message = "Название должно быть от 1 до 50 символов!")
    private String name;
    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(min = 3, max = 50, message = "ФИО должно быть от 3 до 50 символов!")
    private String authorName;
    @Min(value = 1, message = "Год должен быть меньше 0!")
    private int year;


    public Book() {
    }

    public Book(int id, String name, String authorName, int year) {
        this.id = id;
        this.name = name;
        this.authorName = authorName;
        this.year = year;
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

}
