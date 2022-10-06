package com.example.nikola.service;

import com.example.nikola.exception.MyException;
import com.example.nikola.model.User;
import com.example.nikola.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @SneakyThrows
    @Override
    @Transactional
    public Object saveUser(User user) {
        String userName = user.getName();
        if (userRepository.existsByName(userName)) {
            return new MyException("User already exists").getMessage();
        }
        if ((userName == null) || (userName.length() == 0)) {
            return new MyException("User can not be null").getMessage();
        } else {
            user.setName(userName);
            user.setState("CREATED");
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Object deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return "User REMOVED";
        }
        return new MyException("There isn't user in DB").getMessage();
    }

    @Override
    @Transactional
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Transactional
    public Object updateUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setState("UPDATED");
            User updatedUser = userRepository.save(user);
            return updatedUser;
        }
        return new MyException("There isn't user in DB").getMessage();
    }
}
