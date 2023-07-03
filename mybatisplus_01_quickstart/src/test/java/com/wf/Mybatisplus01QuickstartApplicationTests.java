package com.wf;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wf.dao.UserDao;
import com.wf.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Mybatisplus01QuickstartApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void testGetAll() {
        List<User> users = userDao.selectList(null);
        System.out.println(users);
        for (User user : users) {
            System.out.println(user);
        }
    }


    @Test
    void testGetById(){
        User user = userDao.selectById(1L);
        System.out.println(user);
    }


    @Test
    void testInsert(){
        User user = new User(null,"wf","wf",21,"18834803580");
        userDao.insert(user);
    }

    @Test
    void testUpdate(){
        User user = new User();
        user.setId(1L);
        user.setName("Tom888");
        user.setPassword("tom888");
        userDao.updateById(user);
    }

    //1675682469619187713
    @Test
    void testDelete(){
        userDao.deleteById(1675682469619187713L);
    }


    @Test
    void testByPage(){
        IPage page = new Page(1,3);
        userDao.selectPage(page,null);
        System.out.println("当前页:"+page.getCurrent());
        System.out.println("每页显示："+page.getSize());
        System.out.println("一共多少页："+page.getPages());
        System.out.println("一个多少条数据:"+page.getTotal());
        System.out.println("数据:"+page.getRecords());
    }
}
