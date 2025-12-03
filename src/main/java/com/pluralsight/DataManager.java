package com.pluralsight;

import com.pluralsight.models.Actor;
import com.pluralsight.models.Film;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.*;

public class DataManager {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/";
    private Connection connection;

    public DataManager(String database, String username, String password) {
        loadConnection(database, username, password);
    }


    private void loadConnection(String database, String username, String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(URL + database);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Error when loading connection. Exiting application.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public List<Film> getAllFilms(){
        String query = "SELECT * FROM film";
        List<Film> films = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet results = statement.executeQuery();) {
                while (results.next()) {
                    Film film = new Film(
                            results.getString("title"),
                            results.getString("description"),
                            results.getInt("film_ID"),
                            results.getInt("release_year"),
                            results.getInt("length")
                    );
                    films.add(film);
                }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return films;
    }

    public List<Actor> getAllActors(){
        String query = "SELECT * FROM actor";
        List<Actor> actors = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet results = statement.executeQuery();) {
            while (results.next()) {
                Actor actor = new Actor(
                        results.getString("first_name"),
                        results.getString("last_name"),
                        results.getInt("actor_id")
                        );
                actors.add(actor);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return actors;
    }

    public List<Film> getFilmByID(int actorID){
        String query = "SELECT * FROM film f JOIN film_actor fa ON fa.film_id = f.film_id WHERE fa.actor_id = ?;";
        List<Film> films = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, actorID);
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    Film film = new Film(
                            results.getString("title"),
                            results.getString("description"),
                            results.getInt("film_ID"),
                            results.getInt("release_year"),
                            results.getInt("length")
                    );
                    films.add(film);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
