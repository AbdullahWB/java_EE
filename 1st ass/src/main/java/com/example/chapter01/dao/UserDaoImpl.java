package com.example.chapter01.dao;

import com.example.chapter01.model.User;

/**
 * This is the concrete implementation of the UserDao interface.
 * It is intentionally NOT annotated with @Component because we want Spring
 * to create this bean from XML configuration to demonstrate XML-based setup.
 */
public class UserDaoImpl implements UserDao {

    /**
     * Return a sample in-memory user.
     * This keeps the project focused on Spring IoC instead of database setup.
     */
    @Override
    public User findUserById(int id) {
        if (id == 1) {
            return new User(1, "Ada Lovelace");
        }

        // Returning a fallback object keeps the demo predictable for any id.
        return new User(id, "Guest User");
    }
}
