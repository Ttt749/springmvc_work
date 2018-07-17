package com.example.snnu.dao;

import com.example.snnu.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BookDao {
    List<Book> getAll();
    List<Book> queryByPage(@Param("pageNum") int pageNum,
                           @Param("pageSize") int pageSize,
                           @Param("bookPublication") String bookPublication);
    int insertBook(@Param("book") Book book);
}
