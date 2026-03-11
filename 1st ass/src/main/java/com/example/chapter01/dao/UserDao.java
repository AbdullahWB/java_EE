package com.example.chapter01.dao;

import com.example.chapter01.model.User;

/**
 * This interface represents the data-access layer.
 * A service can depend on this abstraction instead of a concrete class,
 * which is one reason dependency injection makes code more flexible.
 */
public interface UserDao {

    /**
     * Find a user by id.
     * In a real project this might call a database, but here we keep it simple.
     */
    User findUserById(int id);
}
