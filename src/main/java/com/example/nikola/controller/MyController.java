package com.example.nikola.controller;

import com.example.nikola.model.User;
import com.example.nikola.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class MyController {
    UserRepository userRepository;
    @Autowired
    public MyController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getusers/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
            Optional<User> user = userRepository.findById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/adduser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        String userName = user.getName();
        if (userRepository.existsByName(userName)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if ( (userName == null) || (userName.length() == 0) ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            try {
                user.setName(userName);
                user.setState("CREATED");
                User saveUser = userRepository.save(user);
                return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        if ( id == null || !userRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            try {
                Optional<User> optionalUser = userRepository.findById(id);
                userRepository.delete(optionalUser.get());
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PutMapping("/updateuser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id) {
        if (id == null || !userRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            try {
                User user = userRepository.findById(id).orElse(null);
                Objects.requireNonNull(user).setState("UPDATED");
                userRepository.save(user);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

}
