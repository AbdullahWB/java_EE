package com.example.library.dao;

import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {

    @Override
    public void addBook() {
        System.out.println("BookDaoImpl: adding a book...");
    }

    @Override
    public void deleteBook() {
        System.out.println("BookDaoImpl: deleting a book...");
        throw new RuntimeException("Simulated exception while deleting book");
    }
}
