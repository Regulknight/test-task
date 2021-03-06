package com.haulmont.testtask.controller;

import com.haulmont.testtask.dao.AuthorService;
import com.haulmont.testtask.dao.BookService;
import com.haulmont.testtask.dao.GenreService;
import com.haulmont.testtask.model.Author;
import com.haulmont.testtask.model.Book;
import com.haulmont.testtask.model.Genre;
import com.haulmont.testtask.view.MainUI;
import com.vaadin.ui.Grid;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zelh on 18.06.17.
 */
public class Controller {
    private AuthorService authorService;
    private GenreService genreService;
    private BookService bookService;
    private MainUI ui;

    public Controller(MainUI ui){
        this.authorService = AuthorService.getInstance();
        this.genreService = GenreService.getInstance();
        this.bookService = BookService.getInstance();
        this.ui = ui;

    }

    public void addBook(Book book){
        try {
            bookService.add(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ui.updateBooksLayout();
    }

    public void setBook(Book book){
        try {
            bookService.set(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ui.updateBooksLayout();
    }

    public void deleteBook(Book book) throws DeleteException {
        try {
            bookService.delete(book.getId());
        } catch (SQLException e) {
            throw new DeleteException();
        }
        ui.updateBooksLayout();
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
        ui.updateGenresLayout();
    }

    public void setGenre(Genre genre){
        try {
            genreService.set(genre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ui.updateGenresLayout();
    }

    public Genre getGenre(long id){
        Genre genre = new Genre();
        try {
            genre = genreService.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genre;
    }
    public void deleteGenre(Genre genre) throws DeleteException {
        try {
            genreService.delete(genre.getId());
        } catch (SQLException e) {
            throw new DeleteException();
        }
        ui.updateGenresLayout();
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
        ui.updateAuthorsLayout();
    }

    public void setAuthor(Author author){
        try {
            authorService.set(author);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ui.updateAuthorsLayout();
    }

    public Author getAuthor(long id){
        Author author = new Author();
        try {
            author = authorService.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    public void deleteAuthor(Author author) throws DeleteException {
        try {
            authorService.delete(author.getId());
        } catch (SQLException e) {
            throw new DeleteException();
        }
        ui.updateAuthorsLayout();
    }

    public List<Author> getAllAuthors(){
        try {
           return authorService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Object[]> getGenresStats(){
        List<Object[]> result = new ArrayList<Object[]>();
        try {
            for (Genre genre: genreService.getAll()){
                int stat = 0;
                for (Book book: bookService.getAll())
                    if (book.getGenre().equals(genre))
                        stat++;
                result.add(new Object[]{genre.getId(), stat});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateUI(){
        ui.updateAuthorsLayout();
        ui.updateGenresLayout();
        ui.updateBooksLayout();
    }

}

