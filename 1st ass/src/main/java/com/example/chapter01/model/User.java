package com.example.chapter01.model;

/**
 * This is a simple POJO (Plain Old Java Object).
 * In Spring, many business objects are still regular Java classes like this.
 */
public class User {

    // A small amount of user data is enough for a beginner example.
    private final int id;
    private final String name;

    /**
     * The constructor creates a User object with an id and a name.
     * Spring can manage objects like this, but it does not have to.
     */
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter methods expose object state in a controlled way.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter methods expose object state in a controlled way.
     */
    public String getName() {
        return name;
    }

    /**
     * toString makes console output easier to read during the demo.
     */
    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "'}";
    }
}
