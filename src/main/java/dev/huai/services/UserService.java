package dev.huai.services;

import dev.huai.daos.UserDao;
import dev.huai.models.User;

import java.math.BigDecimal;

public interface UserService {

    public boolean addUser(User user);

    public boolean updateUserPassword(Integer user_id, String password);

    public boolean updateBalanceDeposit(Integer user_id, BigDecimal deposit);

    public boolean updateBalanceCashOut(Integer user_id, BigDecimal cash_out_amount);

    public User getUserByCredential(Integer user_id, String password);
}
