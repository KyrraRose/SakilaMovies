package com.pluralsight.models;

public class Actor {
    private String firstName,lastName;
    private int actorId;

    public Actor(String firstName, String lastName, int actorId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.actorId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }
}
