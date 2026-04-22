package com.example.library.service;

import com.example.library.dao.BookDao;
import com.example.library.dao.RecordDao;
import com.example.library.pojo.Book;
import com.example.library.pojo.Record;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final BookDao bookDao;
    private final RecordDao recordDao;

    public LibraryServiceImpl(BookDao bookDao, RecordDao recordDao) {
        this.bookDao = bookDao;
        this.recordDao = recordDao;
    }

    @Override
    @Transactional
    public void borrowBook(String bookName, String borrower) {
        Book book = bookDao.findBookByName(bookName);

        if (book == null) {
            throw new RuntimeException("Book not found");
        }

        if (book.getStock() <= 0) {
            throw new RuntimeException("Insufficient stock");
        }

        bookDao.updateStock(book.getId(), book.getStock() - 1);
        recordDao.addRecord(new Record(bookName, borrower, "BORROWED"));

        // Uncomment to test rollback:
        // int x = 1 / 0;
    }

    @Override
    @Transactional
    public void returnBook(String bookName, String borrower) {
        Book book = bookDao.findBookByName(bookName);
        Integer recordId = recordDao.findLatestBorrowedRecordId(bookName, borrower);

        if (book == null) {
            throw new RuntimeException("Book not found");
        }

        if (recordId == null) {
            throw new RuntimeException("Borrow record not found");
        }

        bookDao.updateStock(book.getId(), book.getStock() + 1);
        recordDao.updateRecordStatus(recordId, "RETURNED");
    }
}
