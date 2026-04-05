package com.miki.jobboard.service;

import com.miki.jobboard.entity.User;

import java.util.List;

public interface UserService {
    User register(User user);

    User getUserById(Long id);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long id);

}
