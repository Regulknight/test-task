package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.Book;
import com.haulmont.testtask.model.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private static BookService instance;
    private PreparedStatement statement;
    private Connection connection;

    public static BookService getInstance(){
        if (instance == null){
            instance = new BookService();
        }
        return instance;
    }

    private BookService(){
        connection = DBConnector.getInstance();
    }

    public void add(Book book) throws SQLException {
        String query = "INSERT INTO PUBLIC.BOOK(NAME, BYEAR, CITY, AUTHOR, GENRE, PUBLISHER) VALUES(?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(query);
        statement.setString(1, book.getName());
        statement.setString(2, String.valueOf(book.getYear()));
        statement.setString(3, book.getCity());
        statement.setString(4, String.valueOf(book.getAuthor().getId()));
        statement.setString(5, String.valueOf(book.getGenre().getId()));
        statement.setString(6, book.getPublisher());
        statement.execute();
    }

    public Book get(long id) throws SQLException {
        String query = "SELECT * FROM PUBLIC.BOOK WHERE ID = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(id));
        ResultSet rs = statement.executeQuery();
        if (rs.next()){
            Book book = new Book();
            book.setId(rs.getLong("ID"));
            book.setName(rs.getString("NAME"));
            book.setYear(rs.getInt("BYEAR"));
            book.setCity(rs.getString("CITY"));
            book.setAuthor(AuthorService.getInstance().get(rs.getLong("AUTHOR")));
            book.setGenre(GenreService.getInstance().get(rs.getLong("GENRE")));
            book.setPublisher(rs.getString("PUBLISHER"));
            return book;
        }else
            return null;
    }

    public void set(Book book) throws SQLException {
        String query = "UPDATE PUBLIC.BOOK SET NAME = ?, BYEAR = ?, CITY = ?, AUTHOR = ?, GENRE = ?, PUBLISHER = ? " +
                "WHERE ID = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, book.getName());
        statement.setString(2, String.valueOf(book.getYear()));
        statement.setString(3, book.getCity());
        statement.setString(4, String.valueOf(book.getAuthor().getId()));
        statement.setString(5, String.valueOf(book.getGenre().getId()));
        statement.setString(6, book.getPublisher());
        statement.setString(7, String.valueOf(book.getId()));
        statement.execute();
    }

    public void delete(long id) throws SQLException {
        String query = "DELETE FROM PUBLIC.BOOK WHERE ID = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(id));
        statement.execute();
    }

    public List<Book> getAll() throws SQLException {
        String query = "SELECT * FROM PUBLIC.BOOK";
        statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        List<Book> result = new ArrayList();
        while (rs.next()){
            Book book = new Book();
            book.setId(rs.getLong("ID"));
            book.setName(rs.getString("NAME"));
            book.setYear(rs.getInt("BYEAR"));
            book.setCity(rs.getString("CITY"));
            book.setAuthor(AuthorService.getInstance().get(rs.getLong("AUTHOR")));
            book.setGenre(GenreService.getInstance().get(rs.getLong("GENRE")));
            book.setPublisher(rs.getString("PUBLISHER"));
            result.add(book);
        }
        return result;
    }
}
