package com.example.library.pojo;

public class Record {
    private Integer id;
    private String bookName;
    private String borrower;
    private String status;

    public Record() {
    }

    public Record(Integer id, String bookName, String borrower, String status) {
        this.id = id;
        this.bookName = bookName;
        this.borrower = borrower;
        this.status = status;
    }

    public Record(String bookName, String borrower, String status) {
        this.bookName = bookName;
        this.borrower = borrower;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}