package com.example.library;

import com.example.library.dao.BookDao;
import com.example.library.dao.RecordDao;
import com.example.library.pojo.Book;
import com.example.library.pojo.Record;
import com.example.library.service.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LibraryServiceTest {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private RecordDao recordDao;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.update("DELETE FROM record");
        jdbcTemplate.update("DELETE FROM book");
        bookDao.addBook(new Book("Spring in Action", 2));
    }

    @Test
    void testSuccessfulBorrowing() {
        libraryService.borrowBook("Spring in Action", "Abdul");

        Book book = bookDao.findBookByName("Spring in Action");
        assertEquals(1, book.getStock());

        List<Record> records = recordDao.findAllRecords();
        assertTrue(records.stream().anyMatch(r -> r.getBookName().equals("Spring in Action")
                && r.getBorrower().equals("Abdul")
                && r.getStatus().equals("BORROWED")));
    }

    @Test
    void testInsufficientStockShouldRollback() {
        // first make stock 0
        Book book = bookDao.findBookByName("Spring in Action");
        bookDao.updateStock(book.getId(), 0);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> libraryService.borrowBook("Spring in Action", "Ali"));

        assertEquals("Insufficient stock", ex.getMessage());

        Book updated = bookDao.findBookByName("Spring in Action");
        assertEquals(0, updated.getStock());

        List<Record> records = recordDao.findAllRecords();
        assertFalse(records.stream().anyMatch(r -> r.getBookName().equals("Spring in Action")
                && r.getBorrower().equals("Ali")));
    }

    @Test
    void testReturningOperation() {
        libraryService.borrowBook("Spring in Action", "Sara");
        libraryService.returnBook("Spring in Action", "Sara");

        Book book = bookDao.findBookByName("Spring in Action");
        assertEquals(2, book.getStock());

        List<Record> records = recordDao.findAllRecords();
        assertTrue(records.stream().anyMatch(r -> r.getBookName().equals("Spring in Action")
                && r.getBorrower().equals("Sara")
                && r.getStatus().equals("RETURNED")));
    }

    @Test
    void testBorrowingMissingBookShouldThrowBookNotFound() {
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> libraryService.borrowBook("Missing Book", "Omar"));

        assertEquals("Book not found", ex.getMessage());
    }

    @Test
    void testReturningOneBorrowOnlyUpdatesOneRecord() {
        libraryService.borrowBook("Spring in Action", "Sara");
        libraryService.borrowBook("Spring in Action", "Sara");

        libraryService.returnBook("Spring in Action", "Sara");

        Book book = bookDao.findBookByName("Spring in Action");
        assertEquals(1, book.getStock());

        List<Record> records = recordDao.findAllRecords();
        long borrowedCount = records.stream()
                .filter(r -> r.getBookName().equals("Spring in Action")
                        && r.getBorrower().equals("Sara")
                        && r.getStatus().equals("BORROWED"))
                .count();
        long returnedCount = records.stream()
                .filter(r -> r.getBookName().equals("Spring in Action")
                        && r.getBorrower().equals("Sara")
                        && r.getStatus().equals("RETURNED"))
                .count();

        assertEquals(1, borrowedCount);
        assertEquals(1, returnedCount);
    }
}
