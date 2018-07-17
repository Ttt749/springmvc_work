package com.example.snnu.entity;

import java.util.Date;


public class Book {
    private long bookId;
    private String bookName;
    private String bookAuthor;
    private int pageSize;
    private String bookPublication;
    private Date bookPubData;
    private String bookImage;

    public Book() {
    }

    public Book(long bookId, String bookName, String bookAuthor, int pageSize, String bookPublication, Date bookPubData, String bookImage) {

        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.pageSize = pageSize;
        this.bookPublication = bookPublication;
        this.bookPubData = bookPubData;
        this.bookImage = bookImage;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getBookPublication() {
        return bookPublication;
    }

    public void setBookPublication(String bookPublication) {
        this.bookPublication = bookPublication;
    }

    public Date getBookPubData() {
        return bookPubData;
    }

    public void setBookPubData(Date bookPubData) {
        this.bookPubData = bookPubData;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", pageSize=" + pageSize +
                ", bookPublication='" + bookPublication + '\'' +
                ", bookPubData=" + bookPubData +
                ", bookImage='" + bookImage + '\'' +
                '}';
    }
}
