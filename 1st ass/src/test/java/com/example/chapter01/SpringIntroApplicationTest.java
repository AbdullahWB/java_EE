package com.example.chapter01;

import com.example.chapter01.dao.UserDao;
import com.example.chapter01.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This test proves that the web application starts,
 * the UI page renders, and Spring wires the beans correctly.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringIntroApplicationTest {

    // Spring injects a random free port so the test can call the running app.
    @LocalServerPort
    private int port;

    // TestRestTemplate is a small HTTP client designed for integration tests.
    @Autowired
    private TestRestTemplate restTemplate;

    // These beans should still be available inside the Spring container.
    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    /**
     * This test calls the home page and checks that the expected learning
     * example is visible in the rendered HTML.
     */
    @Test
    @DisplayName("The UI page should show the Spring IoC example")
    void homePageShouldRenderSpringExample() {
        String html = restTemplate.getForObject("http://localhost:" + port + "/", String.class);

        assertNotNull(html);
        assertTrue(html.contains("Spring IoC and DI Demo"));
        assertTrue(html.contains("XML bean result:"));
        assertTrue(html.contains("Welcome to Spring, Ada Lovelace!"));
        assertTrue(html.contains("Ada Lovelace"));
    }

    /**
     * This test checks the underlying beans directly as well,
     * so we still validate the XML + annotation integration.
     */
    @Test
    @DisplayName("The XML bean and annotation-based service should still work together")
    void beansShouldStillBeWiredCorrectly() {
        assertNotNull(userService);
        assertNotNull(userDao);
        assertEquals("Ada Lovelace", userDao.findUserById(1).getName());
        assertEquals("Welcome to Spring, Ada Lovelace!", userService.getWelcomeMessage(1));
    }
}
