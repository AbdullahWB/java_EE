package com.example.library.dao;

import com.example.library.pojo.Book;

public interface BookDao {
    void addBook(Book book);

    void updateStock(int id, int stock);

    Book findBookByName(String name);
}

