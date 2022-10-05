package com.example.nikola.controller;

import com.example.nikola.model.User;
import com.example.nikola.repository.PostgresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class MyController {

    @Autowired
    PostgresRepository postgresRepository;

    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = postgresRepository.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getusers/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {

        try {
            User user = postgresRepository.findById(id).orElse(null);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/adduser/{name}")
    public ResponseEntity<User> addUser(@PathVariable("name") String name) {

        User user = new User();
        if ( postgresRepository.findByName(name) != null ) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            user.setName(name);
            user.setState("CREATED");
            User saveUser = postgresRepository.save(user);
            return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {

        Optional<User> user = postgresRepository.findById(id);
        postgresRepository.delete(user.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateuser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id) {

        User user = postgresRepository.findById(id).orElse(null);
        user.setState("UPDATED");
        postgresRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
