package org.example.library.models;


import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


public class Person {

    private int personId;

    @NotEmpty(message = "ФИО не может быть пустым!")
    @Size(min = 3, max = 50, message = "ФИО должно быть от 3 до 50 символов!")
    private String fullName;

    @Min(value = 1900, message = "Год рождения должен быть более чем 1900!")
    private int yearOfBirth;

    public Person() {
    }

    public Person(int personId, String fullName) {
        this.personId = personId;
        this.fullName = fullName;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
