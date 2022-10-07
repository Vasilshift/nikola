package com.example.nikola.controller;

import com.example.nikola.model.User;
import com.example.nikola.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserServiceImpl userServiceImpl;
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/getusers")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userServiceImpl.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/getusers/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        Object user = userServiceImpl.getUser(id);
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
        Object saveUser = userServiceImpl.updateUser(id);
        return new ResponseEntity<>(saveUser, HttpStatus.OK);

    }

}
