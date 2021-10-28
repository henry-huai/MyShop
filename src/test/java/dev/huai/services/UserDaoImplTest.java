package dev.huai.services;

import dev.huai.daos.UserDao;
import dev.huai.daos.UserDaoImpl;
import dev.huai.models.User;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.*;
public class UserDaoImplTest {
    private UserDao userDao = new UserDaoImpl();

    @Test
    public void TestAddUser(){
        User user = new User();
        user.setLastName("A");
        user.setFirstName("B");
        user.setPassword("1234");
        user.setEmail("A@gmail.com");
        assertTrue(userDao.addUser(user));
    }

    @Test
    public void TestUpdateUserPassword(){
        assertTrue(userDao.updateUserPassword(1, "4567"));
    }

    @Test
    public void TestUpdateBalanceDeposit(){
        assertTrue(userDao.updateBalanceDeposit(1, new BigDecimal(100)));
    }

    @Test
    public void TestUpdateBalanceCashOut(){
        assertTrue(userDao.updateBalanceCashOut(1,new BigDecimal(20)));
    }

    @Test
    public void TestGetUserByCredential(){
        assertEquals(new User(1,"B", "A", "4567", false, "A@gmail.com"), userDao.getUserByCredential(1, "4567"));

    }

}
