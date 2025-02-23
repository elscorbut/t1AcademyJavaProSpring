package ru.academy.service;

import org.springframework.stereotype.Service;
import ru.academy.dao.UserDao;
import ru.academy.domain.User;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User create(User user) {
        return userDao.create(user);
    }

    public int deleteById(Long userId) {
        return userDao.deleteById(userId);
    }

    public int updateUsernameById(Long userId, String newUsername) {
        return userDao.updateUsernameById(userId, newUsername);
    }

    public User findById(Long userId) {
        return userDao.findById(userId);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}
