package com.pluralsight;

public class Main {

    public static void main(String[] args) {
        String username = System.getenv("USER");
        String password = System.getenv("PASS");
        DataManager dataManager = new DataManager("sakila", username, password);
        App.run(dataManager);
    }
}
