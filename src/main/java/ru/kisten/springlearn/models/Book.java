package ru.kisten.springlearn.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Book {
    private int id;
    private int readerID;
    @NotEmpty(message = "name should not be empty")
    private String name;
    @NotEmpty(message = "author should not be empty")
    private String author;
    @Min(value = 0, message = "age should be greater or equals zero")
    private int year;

    public Book(int id, int readerID, String name, String author, int year) {
        this.id = id;
        this.readerID = readerID;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReaderID() {
        return readerID;
    }

    public void setReaderID(int readerID) {
        this.readerID = readerID;
    }
}
