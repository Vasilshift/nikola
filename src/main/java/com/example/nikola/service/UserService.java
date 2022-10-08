package com.example.nikola.service;

import com.example.nikola.exception.MyException;
import com.example.nikola.model.User;
import com.example.nikola.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Object createNewUser(User user) {
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

    public Object deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setState("DELETED");
            User deletedUser = new User(user.getId(), user.getName(), user.getState());
            userRepository.deleteById(id);
            return deletedUser;
        } else {
            return new MyException("User does not exist").getMessage();
        }
    }

    public Object getUser(Long id) {
        return userRepository.findById(id);
    }

    public Object updateUser(Long id) {
        if (userRepository.existsById(id)) {
            User user = userRepository.findById(id).orElse(null);
            assert user != null;
            user.setState("UPDATED");
            return userRepository.save(user);
        } else {
            return new MyException("There isn't user in DB").getMessage();
        }
    }
}
