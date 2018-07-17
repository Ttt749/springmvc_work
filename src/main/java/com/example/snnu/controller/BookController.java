package com.example.snnu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.snnu.dao.BookDao;
import com.example.snnu.entity.Book;
import com.example.snnu.entity.User;
import com.example.snnu.util.ComputerId;
import com.example.snnu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/android-api/book")
public class BookController {
    @Autowired
    private BookDao bookDao;
    @RequestMapping(value = "/queryByPage", method = RequestMethod.POST)
    public Result getBooks(@RequestParam("pageNum")String pageNum,@RequestParam("pageSize")String pageSize){
        Result result = new Result();
        /**
         *
         * 第9题
         *
         *
         */
        List<Book> books = bookDao.queryByPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize),"陕师大出版社");
        if(books!=null && books.size()>0){
            result = new Result(1,"成功",books);
        }else{
            result = new Result(0,"失败",null);
        }
        return result;
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result register(@RequestParam("data") String data){
        Result result = new Result();
        Book book = JSONObject.toJavaObject(JSON.parseObject(data),Book.class);
        book.setBookImage("1.jpg");
        int insertcode = 0;
        insertcode = bookDao.insertBook(book);
        if(insertcode>0){
            result = new Result(0,"成功",null);
        }else{
            result = new Result(1,"失败",null);
        }
        return result;
    }
   }
