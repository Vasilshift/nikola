package com.example.nikola.controller;

import com.example.nikola.model.User;
import com.example.nikola.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

//    @PostMapping("/user")
//    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto userDto) {
//        Object saveUser = userService.saveUser(user);
//        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/deleteUser/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
//        Object deleteUser = userService.deleteUser(id);
//        return new ResponseEntity<>(deleteUser, HttpStatus.OK);
//
//    }
//
//    @PutMapping("/updateUser/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable("id") Long id) {
//        Object saveUser = userService.updateUser(id);
//        return new ResponseEntity<>(saveUser, HttpStatus.OK);
//
//    }

}
