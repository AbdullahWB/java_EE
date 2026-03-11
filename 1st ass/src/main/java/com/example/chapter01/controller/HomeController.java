package com.example.chapter01.controller;

import com.example.chapter01.dao.UserDao;
import com.example.chapter01.model.User;
import com.example.chapter01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * A controller handles incoming web requests.
 * In this case, it prepares data for the home page so the user can
 * see the Spring container working inside a browser.
 */
@Controller
public class HomeController {

    // This bean is created through annotation-based configuration.
    private final UserService userService;

    // This bean is created through XML-based configuration.
    private final UserDao userDao;

    /**
     * Constructor injection lets Spring provide both dependencies.
     * This keeps the controller focused on presentation logic.
     */
    @Autowired
    public HomeController(UserService userService, UserDao userDao) {
        this.userService = userService;
        this.userDao = userDao;
    }

    /**
     * This method responds to GET requests for the home page.
     * The returned string "index" tells Spring MVC to render index.html.
     */
    @GetMapping("/")
    public String showHomePage(Model model) {
        User xmlManagedUser = userDao.findUserById(1);

        // Add values to the model so Thymeleaf can display them in the HTML page.
        model.addAttribute("pageTitle", "Spring IoC and DI Demo");
        model.addAttribute("xmlBeanResult", xmlManagedUser);
        model.addAttribute("serviceMessage", userService.getWelcomeMessage(1));
        model.addAttribute("explanation",
                "The DAO bean comes from XML, while the service and controller come from annotations.");

        return "index";
    }
}
