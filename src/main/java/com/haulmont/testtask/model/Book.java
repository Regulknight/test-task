package com.haulmont.testtask.model;

import java.sql.Date;
import java.time.Year;

public class Book {
    Long id;
    String name;
    Author author;
    Genre genre;
    String publisher;
    Integer year;
    String city;

    public Book() {
    }

    public Book(Long id, String name, Author author, Genre genre, String publisher, Integer year, String city) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.year = year;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (!getId().equals(book.getId())) return false;
        if (!getName().equals(book.getName())) return false;
        if (!getAuthor().equals(book.getAuthor())) return false;
        if (!getGenre().equals(book.getGenre())) return false;
        if (!getPublisher().equals(book.getPublisher())) return false;
        if (!getYear().equals(book.getYear())) return false;
        return getCity().equals(book.getCity());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAuthor().hashCode();
        result = 31 * result + getGenre().hashCode();
        result = 31 * result + getPublisher().hashCode();
        result = 31 * result + getYear().hashCode();
        result = 31 * result + getCity().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", publisher='" + publisher + '\'' +
                ", year=" + year +
                ", city='" + city + '\'' +
                '}';
    }
}
