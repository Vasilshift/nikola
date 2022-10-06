package com.example.nikola.controller;

import com.example.nikola.model.User;
import com.example.nikola.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MyController {
    private final UserServiceImpl userServiceImpl;
    @Autowired
    public MyController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userServiceImpl.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/getusers/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User user = userServiceImpl.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/adduser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        Object saveUser = userServiceImpl.saveUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.OK);
    }

    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        Object deleteUser = userServiceImpl.deleteUser(id);
        return new ResponseEntity<>(deleteUser, HttpStatus.OK);

    }

    @PutMapping("/updateuser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id) {
//        if (id == null || !userRepository.existsById(id)) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } else {
//            try {
//                User user = userRepository.findById(id).orElse(null);
//                Objects.requireNonNull(user).setState("UPDATED");
//                userRepository.save(user);
//                return new ResponseEntity<>(HttpStatus.OK);
//            } catch (Exception e) {
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//        }

        Object saveUser = userServiceImpl.updateUser(id);
        return new ResponseEntity<>(saveUser, HttpStatus.OK);

    }

}
