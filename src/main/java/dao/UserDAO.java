package dao;

import entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    User getUser(int userId);
    int updateUser(int userOldId, User userNew);
    int deleteUser(int userId);
}
