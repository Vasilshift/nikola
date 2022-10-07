package com.example.nikola.service;

import com.example.nikola.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    Object saveUser(User user);
    Object deleteUser(Long id);
    Object getUser(Long id);
}
