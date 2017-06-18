package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorService {
    private static AuthorService instance;
    private PreparedStatement statement;
    private Connection connection;

    public static AuthorService getInstance(){
        if (instance == null){
            instance = new AuthorService();
        }
        return instance;
    }

    private AuthorService(){
        connection = DBConnector.getInstance();
    }

    public void add(Author author) throws SQLException {
        String query = "INSERT INTO TABLE PUBLIC.AUTHOR(FNAME, LNAME, PATRON) VALUES(?, ?, ?)";
        statement = connection.prepareStatement(query);
        statement.setString(1, author.getFname());
        statement.setString(2, author.getLname());
        statement.setString(3, author.getPatronymic());
        statement.executeQuery();
    }

    public Author get(long id) throws SQLException {
        String query = "SELECT * FROM PUBLIC.GENRE WHERE ID = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(id));
        ResultSet rs = statement.executeQuery();
        if (rs.next()){
            Author author = new Author();
            author.setId(rs.getLong("ID"));
            author.setFname(rs.getString("FNAME"));
            author.setLname(rs.getString("LNAME"));
            author.setPatronymic(rs.getString("PATRON"));
            return author;
        }else
            return null;
    }

    public void delete(long id) throws SQLException {
        String query = "DELETE FROM PUBLIC.AUTHOR WHERE ID = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(id));
        statement.executeQuery();
    }

    public void set(Author author) throws SQLException {
        String query = "UPDATE PUBLIC.AUTHOR SET FNAME = ?, LNAME = ?, PATRON = ? WHERE ID = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, author.getFname());
        statement.setString(2, author.getLname());
        statement.setString(3, author.getPatronymic());
        statement.setString(4, String.valueOf(author.getId()));
        statement.executeQuery();
    }

    public List<Author> getAll() throws SQLException {
        String query = "SELECT * FROM PUBLIC.AUTHOR";
        statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        List<Author> result = new ArrayList();
        while (rs.next()){
            Author author = new Author();
            author.setId(rs.getLong("ID"));
            author.setFname(rs.getString("FNAME"));
            author.setLname(rs.getString("LNAME"));
            author.setPatronymic(rs.getString("PATRON"));
            result.add(author);
        }
        return result;
    }


}
