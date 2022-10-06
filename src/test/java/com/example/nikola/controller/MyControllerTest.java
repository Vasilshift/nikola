package com.example.nikola.controller;

import com.example.nikola.NikolaApplication;
import com.example.nikola.model.User;
import com.example.nikola.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
//@RunWith(NikolaApplication.class)
class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

//    @Test
//    void givenListOfUsers_whenGetAllUsers_thenReturnAllUsers() throws Exception {
//        List<User> listUsers = new ArrayList<>();
//        listUsers.add(new User(1L, "John", "CREATED"));
//        listUsers.add(new User(2L, "Sasha", "CREATED"));
//        //listUsers.add(new User(3L, "Masha", "CREATED"));
//
//        userRepository.saveAll(listUsers);
//        UserRepository mock = org.mockito.Mockito.mock(UserRepository.class);
//        when(mock.findAll()).thenReturn(listUsers);
//
//        //given(userRepository.findAll()).willReturn(listUsers);
//        ResultActions response = mockMvc.perform(post("/api/v1/getusers"));
//        response.andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.size()",
//                        is(listUsers.size())));
//
//    }

    @Test
    void getUser() {
    }

    @Test
    void addUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}