package com.example.library.dao;

import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao {
    @Override
    public void addBook() {
        System.out.println("Adding a book to the library.");
    }

    @Override
    public void removeBook() {
        System.out.println("Removing a book from the library.");

        int x = 10 / 0;
    }
}
