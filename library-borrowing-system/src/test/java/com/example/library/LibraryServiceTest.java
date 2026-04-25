package com.example.library;

import com.example.library.dao.BookDao;
import com.example.library.dao.RecordDao;
import com.example.library.pojo.Book;
import com.example.library.pojo.Record;
import com.example.library.service.LibraryService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LibraryServiceTest {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private RecordDao recordDao;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeAll
    static void printSuiteHeader() {
        line("============================================================");
        line(" Library Borrowing Management System - Integration Test Run ");
        line("============================================================");
    }

    @BeforeEach
    void setUp() {
        jdbcTemplate.update("DELETE FROM record");
        jdbcTemplate.update("DELETE FROM book");
        bookDao.addBook(new Book("Spring in Action", 2));
    }

    @Test
    @Order(1)
    @DisplayName("Borrow book successfully")
    void testSuccessfulBorrowing() {
        line("");
        line("[TEST 1] Borrow book successfully");

        Book before = bookDao.findBookByName("Spring in Action");
        line("  GIVEN : book='Spring in Action', stock=" + before.getStock());

        line("  WHEN  : borrower='Abdul' borrows the book");
        libraryService.borrowBook("Spring in Action", "Abdul");

        Book after = bookDao.findBookByName("Spring in Action");
        line("  THEN  : stock changed " + before.getStock() + " -> " + after.getStock());
        assertEquals(1, after.getStock());

        List<Record> records = recordDao.findAllRecords();
        boolean found = records.stream().anyMatch(r -> r.getBookName().equals("Spring in Action") &&
                r.getBorrower().equals("Abdul") &&
                r.getStatus().equals("BORROWED"));

        line("  THEN  : record inserted [book=Spring in Action, borrower=Abdul, status=BORROWED]");
        assertTrue(found);

        line("  RESULT: PASS");
    }

    @Test
    @Order(2)
    @DisplayName("Borrow blocked when stock is zero")
    void testInsufficientStock() {
        line("");
        line("[TEST 2] Borrow blocked when stock is zero");

        Book book = bookDao.findBookByName("Spring in Action");
        bookDao.updateStock(book.getId(), 0);

        Book before = bookDao.findBookByName("Spring in Action");
        line("  GIVEN : book='Spring in Action', stock=" + before.getStock());

        line("  WHEN  : borrower='Ali' tries to borrow the book");
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> libraryService.borrowBook("Spring in Action", "Ali"));

        line("  THEN  : exception = '" + ex.getMessage() + "'");
        assertEquals("Insufficient stock", ex.getMessage());

        Book after = bookDao.findBookByName("Spring in Action");
        line("  THEN  : stock remains " + after.getStock());
        assertEquals(0, after.getStock());

        List<Record> records = recordDao.findAllRecords();
        boolean inserted = records.stream().anyMatch(r -> r.getBookName().equals("Spring in Action") &&
                r.getBorrower().equals("Ali"));

        line("  THEN  : no new borrow record inserted");
        assertFalse(inserted);

        line("  RESULT: PASS");
    }

    @Test
    @Order(3)
    @DisplayName("Return borrowed book successfully")
    void testReturningOperation() {
        line("");
        line("[TEST 3] Return borrowed book successfully");

        Book before = bookDao.findBookByName("Spring in Action");
        line("  GIVEN : book='Spring in Action', stock=" + before.getStock());

        line("  WHEN  : borrower='Sara' borrows then returns the book");
        libraryService.borrowBook("Spring in Action", "Sara");
        libraryService.returnBook("Spring in Action", "Sara");

        Book after = bookDao.findBookByName("Spring in Action");
        line("  THEN  : stock changed 2 -> 1 -> " + after.getStock());
        assertEquals(2, after.getStock());

        List<Record> records = recordDao.findAllRecords();
        boolean returned = records.stream().anyMatch(r -> r.getBookName().equals("Spring in Action") &&
                r.getBorrower().equals("Sara") &&
                r.getStatus().equals("RETURNED"));

        line("  THEN  : record status changed BORROWED -> RETURNED");
        assertTrue(returned);

        line("  RESULT: PASS");
    }

    @AfterAll
    static void printSuiteFooter() {
        line("");
        line("============================================================");
        line(" Test suite finished ");
        line("============================================================");
    }

    private static void line(String text) {
        System.out.println(text);
    }
}