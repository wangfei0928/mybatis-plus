package com.wf;

import com.wf.dao.UserDao;
import com.wf.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Mybatisplus03DmlApplicationTests {

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
        User user = new User(null,"wf","wf",21,"18834803580",1,null,null);
        userDao.insert(user);
    }

    @Test
    void testUpdate(){
        User user = new User();
        user.setId(3L);
        user.setName("Jock888");
        user.setPassword("Jock888");
        user.setVersion(1);
        userDao.updateById(user);
    }


    @Test
    void testUpdate2(){
        User user = userDao.selectById(3L);
        user.setName("Jock666");
        userDao.updateById(user);
    }

    //1675682469619187713
    @Test
    void testDelete(){


//        ArrayList<Long> list = new ArrayList<>();
//        list.add(1676049144441020418L);
//        list.add(1676050662208585729L);
//        //多条数据删除
//        userDao.deleteBatchIds(list);

        userDao.deleteById(2L);
    }

}
