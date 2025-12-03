package com.pluralsight;
import java.sql.*;



public class App {
    private static final int ALL_CATEGORIES = -1;
    private static DataManager dataManager;

    public static void run(DataManager dm) {
        dataManager = dm;

        do {
            UserInterface.displayMainMenu();

            switch(UserInterface.getUserInt("Enter a number option: ")) {
                case 1 -> handleDisplayActors(ALL_CATEGORIES);
                case 2 -> handleDisplayFilms();
                case 3 -> handleDisplayCategories();
                case 0 -> exitProgram();
                default -> {
                    UserInterface.clearScreen();
                    System.out.println("Invalid option entered, please try again!\n");
                }
            }

        } while (true);
    }
    public static void handleDisplayActors(){
        
    }

    private static void exitProgram() {
        UserInterface.clearScreen();
        System.out.println("\t🎬 Cut! 🎬");
        System.out.println("\nThanks for visiting Sakila Movies!! \n");

        dataManager.close();
        UserInterface.closeScanner();
        System.exit(0);
    }



}
