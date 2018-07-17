package com.example.snnu.dao;

import com.example.snnu.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by WT on 2018/5/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void getAll() throws Exception {
        List<User> users = userDao.getAll();
        System.out.println(users);
    }

    @Test
    public void queryById() throws Exception {
        User user = new User();
        user.setId(41512199);
        user.setUserName("王甜");
        user.setPassWord("41512199");
        user.setBirthday(new Date());
        user.setAge(18);

        userDao.insert(user);

        System.out.print(userDao.queryById("王甜"));
    }

    @Test
    public void insert() throws Exception {
        // 当前文件系统类
        FileSystemView fsv = FileSystemView.getFileSystemView();
        // 列出所有windows 磁盘
        File[] fs = File.listRoots();
        // 显示磁盘卷标
        for (int i = 0; i < fs.length; i++) {
            System.out.println(fsv.getSystemDisplayName(fs[i]));
        }
        String sn = getSerialNumber("C");
        System.out.println("***硬盘编号***");
        System.out.println(sn);
    }
    public static String getSerialNumber(String drive) {
        String result = "";
        try {
            File file = File.createTempFile("damn", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    + "Set colDrives = objFSO.Drives\n"
                    + "Set objDrive = colDrives.item(\""
                    + drive
                    + "\")\n"
                    + "Wscript.Echo objDrive.SerialNumber"; // see note
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;

            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }

}