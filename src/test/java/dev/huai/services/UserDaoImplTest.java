package dev.huai.services;

import dev.huai.daos.UserDao;
import dev.huai.daos.UserDaoImpl;
import dev.huai.models.User;
import org.junit.Test;

import static junit.framework.TestCase.*;
public class UserDaoImplTest {
    private UserDao userDao = new UserDaoImpl();
    @Test
    public void TestAddUser(){
        User user = new User();
        user.setLastName("Huai");
        user.setFirstName("Renhan");
        user.setPassword("1234");
        user.setEmail("renhan@gmail.com");
        assertTrue(userDao.addUser(user));
    }
}
