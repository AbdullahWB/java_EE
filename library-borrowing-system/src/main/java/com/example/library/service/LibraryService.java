package com.example.library.service;

public interface LibraryService {
    void borrowBook(String bookName, String borrower);

    void returnBook(String bookName, String borrower);
}