package com.pluralsight;
import com.pluralsight.models.*;

import java.util.*;


public class App {
    private static final int ALL_CATEGORIES = -1;
    private static DataManager dataManager;

    public static void run(DataManager dm) {
        dataManager = dm;

        do {
            UserInterface.displayMainMenu();

            switch(UserInterface.getUserInt("Enter a number option: ")) {
                case 1 -> handleSearchByName();
                case 2 -> handleDisplayFimByActor();
                case 3 -> handleDisplayActors();
                case 4 -> handleDisplayFilms();
                case 0 -> exitProgram();
                default -> {
                    UserInterface.clearScreen();
                    System.out.println("Invalid option entered, please try again!\n");
                }
            }

        } while (true);
    }

    public static void handleDisplayActors(){
        List<Actor> actors = dataManager.getAllActors();
        UserInterface.displayActors(actors);
        UserInterface.waitAndContinue();
    }
    public static void handleSearchByName(){
        List<Actor> actors = dataManager.getActorByName(UserInterface.getUserString("Enter actor name: "));
        if (!actors.isEmpty()){
            UserInterface.displayActors(actors);
            UserInterface.waitAndContinue();
        }else{
            System.out.println("No results found, please try again.");
        }
    }

    public static void handleDisplayFilms(){
        List<Film> films = dataManager.getAllFilms();
        UserInterface.displayFilms(films);
        UserInterface.waitAndContinue();
    }

    public static void handleDisplayFimByActor(){
        List<Film> films = dataManager.getFilmByID(UserInterface.getUserInt("Enter actor ID: "));
        UserInterface.displayFilms(films);
        UserInterface.waitAndContinue();
    }

    private static void exitProgram() {
        UserInterface.clearScreen();
        System.out.println("🎬 Cut! 🎬");
        System.out.println("Thanks for visiting Sakila Movies!! \n");

        dataManager.close();
        UserInterface.closeScanner();
        System.exit(0);
    }



}
