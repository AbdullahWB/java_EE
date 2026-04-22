package com.example.library.dao;

import com.example.library.pojo.Record;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecordDaoImpl implements RecordDao {

    private final JdbcTemplate jdbcTemplate;

    public RecordDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addRecord(Record record) {
        String sql = "INSERT INTO record(book_name, borrower, status) VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, record.getBookName(), record.getBorrower(), record.getStatus());
    }

    @Override
    public List<Record> findAllRecords() {
        String sql = "SELECT id, book_name AS bookName, borrower, status FROM record";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Record.class));
    }

    @Override
    public Integer findLatestBorrowedRecordId(String bookName, String borrower) {
        String sql = "SELECT id FROM record WHERE book_name = ? AND borrower = ? AND status = 'BORROWED' ORDER BY id DESC LIMIT 1";
        List<Integer> ids = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("id"), bookName, borrower);
        return ids.isEmpty() ? null : ids.get(0);
    }

    @Override
    public void updateRecordStatus(int id, String status) {
        String sql = "UPDATE record SET status = ? WHERE id = ?";
        jdbcTemplate.update(sql, status, id);
    }
}
