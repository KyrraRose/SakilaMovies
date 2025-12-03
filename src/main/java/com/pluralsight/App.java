package com.pluralsight;
import java.sql.*;
import org.apache.commons.dbcp2.BasicDataSource;
import java.util.Scanner;

public class App {



    private static final Scanner scanner = new Scanner(System.in);

    private static Connection connection = null;

    public static void main(String[] args) {
        String username = System.getenv("USER");
        String password = System.getenv("PASS");
        DataManager dataManager = new DataManager("northwind", username, password);
        run();
    }
    public static void run(){
        System.out.println("Name an actor- we know their movies.");
        displayActors(getUserString("What is the last name of the actor you're looking for?\nType Here: "));
        displayMovieByActor(getUserString("What is the name of the actor you want to see movies featuring?\nType Here: "));
    }
    public static void displayActors(String name){
        String query = "SELECT * FROM actor WHERE last_name LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1,"%"+name+"%");
            try (ResultSet results = statement.executeQuery()){

                    System.out.println("\nHere are your results:");
                    System.out.println("ID    First Name     Last Name");
                    System.out.println("----  -------------  -------------");
                    while (results.next()){
                        System.out.printf("%-4d  %-13s  %-13s%n",
                                results.getInt("actor_id"),
                                results.getString("first_name"),
                                results.getString("last_name"));
                    }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void displayMovieByActor(String name){
        String query = "SELECT * FROM film ft JOIN film_actor fa ON fa.film_id = ft.film_id JOIN actor a ON a.actor_id = fa.actor_id WHERE CONCAT(a.first_name, ' ', a.last_name) LIKE ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1,"%"+name+"%");
            try (ResultSet results = statement.executeQuery()){
                    System.out.printf("Here are your results for %s:",name);
                    System.out.println("\nID     Title                         Description");
                    System.out.println("-----  ----------------------------  -------------------------------------");
                    while (results.next()){
                        System.out.printf("%-5d  %-28s  %s%n",
                                results.getInt("release_year"),
                                results.getString("title"),
                                results.getString("description"));
                    }

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUserString(String prompt){
        System.out.println("\n-------\n");
        System.out.print(prompt);
        return scanner.nextLine().trim().toUpperCase();
    }


}
