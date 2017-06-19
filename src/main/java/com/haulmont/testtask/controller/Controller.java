package com.haulmont.testtask.controller;

import com.haulmont.testtask.dao.AuthorService;
import com.haulmont.testtask.dao.BookService;
import com.haulmont.testtask.dao.GenreService;
import com.haulmont.testtask.model.Author;
import com.haulmont.testtask.model.Book;
import com.haulmont.testtask.model.Genre;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zelh on 18.06.17.
 */
public class Controller {
    private AuthorService authorService;
    private GenreService genreService;
    private BookService bookService;

    public Controller(){
        authorService = AuthorService.getInstance();
        genreService = GenreService.getInstance();
        bookService = BookService.getInstance();

    }

    public void addBook(Book book){
        try {
            bookService.add(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setBook(Book book){
        try {
            bookService.set(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(long id){
        try {
            bookService.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks(){
        try {
            return bookService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addGenre(Genre genre){
        try {
            genreService.add(genre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setGenre(Genre genre){
        try {
            genreService.set(genre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGenre(long id){
        try {
            genreService.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Genre> getAllGenres(){
        try {
            return genreService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addAuthor(Author author){
        try {
            authorService.add(author);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setAuthor(Author author){
        try {
            authorService.set(author);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAuthor(long id){
        try {
            authorService.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Author> getAllAuthors(){
        try {
           return authorService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
