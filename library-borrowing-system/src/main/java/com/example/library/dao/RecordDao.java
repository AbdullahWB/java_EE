package com.example.library.dao;

import com.example.library.pojo.Record;
import java.util.List;

public interface RecordDao {
    void addRecord(Record record);

    List<Record> findAllRecords();

    Integer findLatestBorrowedRecordId(String bookName, String borrower);

    void updateRecordStatus(int id, String status);
}
