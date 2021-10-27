package dev.huai.daos;

import dev.huai.models.User;

public interface UserDao {
    public boolean addUser(User user);

    public boolean updateUserPassword(String password);

    public boolean updateBalance(User user);

    public User getUserByCredential(Integer user_id, String password);
}
