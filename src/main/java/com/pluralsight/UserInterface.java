package com.pluralsight;

import com.pluralsight.models.Actor;
import com.pluralsight.models.Film;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);


    public static void displayMainMenu(){
        System.out.println("🍿 Welcome to Sakila Movies! 🎞️");
        System.out.println("\t[1] Search Actors by Name");
        System.out.println("\t[2] Search Films by Actor ID");
        System.out.println("\t[3] Display All Actors");
        System.out.println("\t[4] Display All Films");
        System.out.println("\t[0] Exit");
    }

    public static void displayActors(List<Actor> actors){

        System.out.println("\nHere are your results:");
        System.out.println("ID    First Name     Last Name");
        System.out.println("----  -------------  -------------");
        for (Actor a : actors){
            System.out.printf("%-4d  %-13s  %-13s%n",
                    a.getActorId(),
                    a.getFirstName(),
                    a.getLastName());
        }

    }

    public static void displayFilms(List<Film> films){

        System.out.println("\nID     Title                         Description");
        System.out.println("-----  ----------------------------  -------------------------------------");
        for (Film f : films){
            System.out.printf("%-5d  %-28s  %s%n",
                    f.getFilmId(),
                    f.getTitle(),
                    f.getDescription());
        }

    }

    public static String getUserString(String prompt){
        System.out.println("\n-------\n");
        System.out.print(prompt);
        return scanner.nextLine().trim().toUpperCase();
    }

    public static int getUserInt(String prompt){
        System.out.print(prompt);

        boolean notChosen = true;
        int option = -1;

        do {
            try {
                option = scanner.nextInt();
                scanner.nextLine();

                if (option != -1) notChosen = false;

            } catch (Exception e) {
                System.out.print("Invalid Type Entered. " + prompt);
                scanner.nextLine();
            }
        } while (notChosen);

        return option;
    }


    public static void waitForEnter() {
        System.out.print("\n...Press Enter to Continue");
        scanner.nextLine();
    }

    public static void clearScreen() {
        for (int i = 0; i < 60; i++) {
            System.out.println();
        }
    }

    public static void waitAndContinue() {
        waitForEnter();
        clearScreen();
    }

    public static void closeScanner() {
        scanner.close();
    }
}
