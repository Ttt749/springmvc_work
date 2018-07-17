package com.example.snnu.dao;

import com.example.snnu.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserDao {
    List<User> getAll();
    User queryById(@Param("userName") String userName);
    int insert(@Param("user") User user);
}
