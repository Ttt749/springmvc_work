package com.example.snnu.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.snnu.dao.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by WT on 2018/5/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldTest {
    private MockMvc mockMvc;
    @Autowired
    private UserDao userDao;
    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userDao)).build();
    }
    @Test
    public void printTest() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","41512199");
        jsonObject.put("userName","11");
        jsonObject.put("passWord","41512199");
        jsonObject.put("age","11");
        jsonObject.put("sex","11");
        jsonObject.put("birthday",new Date().getTime());
        jsonObject.put("phoneId","11");


        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/android-api/user/register")
                .param("data",jsonObject.toJSONString())
                .accept(MediaType.APPLICATION_JSON)).andDo(print());
    }
    @Test
    public void loginTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/android-api/user/login")
                .param("userName","11")
                .param("passWord","41512199")
                .accept(MediaType.APPLICATION_JSON)).andDo(print());
    }
}