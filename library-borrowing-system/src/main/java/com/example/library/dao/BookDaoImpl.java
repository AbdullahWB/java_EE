package com.example.library.dao;

import com.example.library.pojo.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addBook(Book book) {
        String sql = "INSERT INTO book(name, stock) VALUES(?, ?)";
        jdbcTemplate.update(sql, book.getName(), book.getStock());
    }

    @Override
    public void updateStock(int id, int stock) {
        String sql = "UPDATE book SET stock = ? WHERE id = ?";
        jdbcTemplate.update(sql, stock, id);
    }

    @Override
    public Book findBookByName(String name) {
        String sql = "SELECT id, name, stock FROM book WHERE name = ?";
        List<Book> books = jdbcTemplate.query(
                sql,
                BeanPropertyRowMapper.newInstance(Book.class),
                name);
        return books.isEmpty() ? null : books.get(0);
    }
}
