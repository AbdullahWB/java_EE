package com.example.chapter01.service;

import com.example.chapter01.dao.UserDao;
import com.example.chapter01.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Component tells Spring to detect this class during component scanning
 * and register it as a bean in the IoC container.
 */
@Component
public class UserService {

    // The service depends on the UserDao abstraction, not the implementation.
    private final UserDao userDao;

    /**
     * @Autowired tells Spring to inject the required dependency here.
     * This is constructor injection, which is a common and recommended style.
     */
    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * This method uses the injected DAO to build a friendly message.
     * The service does not create the DAO itself, so control is inverted to Spring.
     */
    public String getWelcomeMessage(int userId) {
        User user = userDao.findUserById(userId);
        return "Welcome to Spring, " + user.getName() + "!";
    }
}
