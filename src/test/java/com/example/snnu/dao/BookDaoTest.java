package com.example.snnu.dao;

import com.example.snnu.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by WT on 2018/5/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookDaoTest {
    @Autowired
    private BookDao bookDao;
    @Test
    public void queryByPage() throws Exception {
        List<Book> bookList = bookDao.queryByPage(0,1,"陕师大出版社");
        System.out.println(bookList);
    }

}