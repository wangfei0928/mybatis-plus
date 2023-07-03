package com.example.mybatisplus_02_dql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.UserDao;
import com.example.domain.User;
import com.example.query.UserQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class Mybatisplus02DqlApplicationTests {

    @Autowired
    private UserDao userDao;

    //方法一：按条件查询
    @Test
    void testAll() {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.lt("age", 18);
        List<User> users = userDao.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }


    //方式二： Lambda格式按条件查询
    @Test
    void testAll2() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.lambda().lt(User::getAge, 18);
        List<User> users = userDao.selectList(userQueryWrapper);
        System.out.println(users);
    }

    //方式三：Lambda格式按条件查询
    @Test
    void testAll3() {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.lt(User::getAge, 18);
        List<User> users = userDao.selectList(lqw);
        System.out.println(users);
    }

    //多个条件（且关系）
    @Test
    void testAll4() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //链式编程
        wrapper.lt(User::getAge, 18).gt(User::getAge, 10);
//        wrapper.gt(User::getAge,10);
        List<User> users = userDao.selectList(wrapper);
        System.out.println(users);
    }


    //多个条件（或关系）
    @Test
    void testAll5() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        //链式编程
        wrapper.lt(User::getAge, 10).or().gt(User::getAge, 30);

        List<User> users = userDao.selectList(wrapper);
        System.out.println(users);
    }

    @Test
    void testAge() {
        UserQuery userQuery = new UserQuery();
//        userQuery.setAge(10);
        userQuery.setAge2(20);

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.lt(User::getAge, userQuery.getAge2());
        if (userQuery.getAge() != null)
            wrapper.gt(User::getAge, userQuery.getAge());
        List<User> users = userDao.selectList(wrapper);
        System.out.println(users);
    }

    @Test
    void testAge2() {
        UserQuery userQuery = new UserQuery();
//        userQuery.setAge(10);
        userQuery.setAge2(20);

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //先判定第一个参数是否是true,如果为true连接当前条件
        wrapper.lt(null != userQuery.getAge2(),User::getAge, userQuery.getAge2());
        wrapper.gt(null!= userQuery.getAge(),User::getAge, userQuery.getAge());
        List<User> users = userDao.selectList(wrapper);
        System.out.println(users);
    }


    //查询投影（只适用于lambda表达式）
    @Test
    void touYing(){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //只适用于lambda表达式
        wrapper.select(User::getId,User::getName,User::getAge);
        List<User> users = userDao.selectList(wrapper);
        System.out.println(users);
    }


    //投影另一种方式
    @Test
    void touYing2(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("id","name","age","tel");
        List<User> users = userDao.selectList(userQueryWrapper);
        System.out.println(users);
    }

    //投影   分组查询
    @Test
    void touYing3(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("count(*) as count,tel").groupBy("tel");
        List<Map<String, Object>> maps = userDao.selectMaps(userQueryWrapper);
        System.out.println(maps);
    }

    //投影   分组查询
    @Test
    void touYing4(){
        QueryWrapper queryWrapper = new QueryWrapper();
        Integer integer = userDao.selectCount(queryWrapper);
        System.out.println(integer);
    }


    //条件查询  =
    @Test
    public void login(){
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<User>();
        userQueryWrapper.eq(User::getName,"Jerry1").eq(User::getPassword,"jerry");
        User user = userDao.selectOne(userQueryWrapper);
        System.out.println(user);

    }


    //条件查询  范围查询
    //lt le gt ge    lt(less than)   le(lesser equals)  gt(greater than)  ge(greater equals)
    @Test
    public void test(){
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<User>();
//        userQueryWrapper.between(User::getAge,10,30);      // between 10 and 30
         userQueryWrapper.le(User::getAge,12);     // <=12
        List<User> users = userDao.selectList(userQueryWrapper);
        System.out.println(users);

    }


    //条件查询  范围查询
    //lt le gt ge    lt(less than)   le(lesser equals)  gt(greater than)  ge(greater equals)
    @Test
    public void test2(){
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<User>();
//        userQueryWrapper.like(User::getName,"J");

//        userQueryWrapper.likeLeft(User::getName,"J");       // %J
        userQueryWrapper.likeRight(User::getName,"J");    // J%
        List<User> users = userDao.selectList(userQueryWrapper);
        System.out.println(users);

    }
}
