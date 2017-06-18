package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zelh on 18.06.17.
 */
public class GenreService {
    private GenreService instance;
    private PreparedStatement statement;
    private Connection connection;

    public GenreService getInstance(){
        if (instance == null){
            instance = new GenreService();
        }
        return instance;
    }

    GenreService(){
        connection = DBConnector.getInstance();
    }

    public void add(String name) throws SQLException {
        String query = "INSERT INTO TABLE GENRE(NAME) VALUES(?)";
        statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.executeQuery();
    }

    public Genre get(long id) throws SQLException {
        String query = "SELECT * FROM GENRE WHERE ID = ?";
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

    public void set(long id, String name) throws SQLException {
        String query = "UPDATE GENRE SET NAME = ? WHERE ID = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, String.valueOf(id));
        statement.executeQuery();
    }

    public void delete(long id) throws SQLException {
        String query = "DELETE FROM GENRE WHERE ID = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(id));
        statement.executeQuery();
    }

    public List<Genre> getAll() throws SQLException {
        String query = "SELECT * FROM GENRE";
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
