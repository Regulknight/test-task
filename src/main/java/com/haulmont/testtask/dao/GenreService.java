package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreService {
    private static GenreService instance;
    private PreparedStatement statement;
    private Connection connection;

    public static GenreService getInstance(){
        if (instance == null){
            instance = new GenreService();
        }
        return instance;
    }

    private GenreService(){
        connection = DBConnector.getInstance();
    }

    public void add(Genre genre) throws SQLException {
        String query = "INSERT INTO TABLE PUBLIC.GENRE(NAME) VALUES(?)";
        statement = connection.prepareStatement(query);
        statement.setString(1, genre.getName());
        statement.executeQuery();
    }

    public Genre get(long id) throws SQLException {
        String query = "SELECT * FROM PUBLIC.GENRE WHERE ID = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(id));
        ResultSet rs = statement.executeQuery();
        if (rs.next()){
            Genre genre = new Genre();
            genre.setId(rs.getLong("ID"));
            genre.setName(rs.getString("NAME"));
            return genre;
        }else
            return null;
    }

    public void set(Genre genre) throws SQLException {
        String query = "UPDATE PUBLIC.GENRE SET NAME = ? WHERE ID = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, genre.getName());
        statement.setString(2, String.valueOf(genre.getName()));
        statement.executeQuery();
    }

    public void delete(long id) throws SQLException {
        String query = "DELETE FROM PUBLIC.GENRE WHERE ID = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(id));
        statement.executeQuery();
    }

    public List<Genre> getAll() throws SQLException {
        String query = "SELECT * FROM PUBLIC.GENRE";
        statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        List<Genre> result = new ArrayList();
        while (rs.next()){
            Genre genre = new Genre();
            genre.setId(rs.getLong("ID"));
            genre.setName(rs.getString("NAME"));
            result.add(genre);
        }
        return result;
    }
}
