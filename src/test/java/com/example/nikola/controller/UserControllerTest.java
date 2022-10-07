package com.example.nikola.controller;

import com.example.nikola.model.User;
import com.example.nikola.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public  class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    User user1;
    User user2;

    @BeforeEach
    void setUp() {
        user1 = new User(1L, "John", "CREATED");
        user2 = new User(2L, "Sasha", "CREATED");

    }

    @Test
    void givenListOfUsers_whenGetAllUsers_thenReturnAllUsers() throws Exception {
        System.out.println(user1 + " " + user2);
        List<User> listUsers = new ArrayList<>();
        listUsers.add(user1);
        listUsers.add(user2);
        UserService mock = org.mockito.Mockito.mock(UserService.class);
        when(mock.getAllUsers()).thenReturn(listUsers);
        mockMvc.perform(get("/api/v1/users"))
            .andExpect(status().is2xxSuccessful());
    }

//    @Test
//    public void givenUser_whenCreateUser_thenReturnSaveUser() throws Exception {
//        given(userService.saveUser(ArgumentMatchers.any(User.class)))
//                .willAnswer(invocation -> invocation.getArgument(0));
//        ResultActions perform = mockMvc.perform(post("/api/v1/adduser")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(user1)));
//        perform.andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$.name",
//                        CoreMatchers.is(user1.getName())));
//    }

//    @Test
//    void givenUserId_whenGetUser_thenReturnUser() throws Exception {
//        //userServiceImpl.saveUser(user1);
//        //Long userId = user1.getId();
//        when(userService.getUser(userId)).thenReturn(user1);
//        mockMvc.perform(get("/api/v1/getusers/{id}", userId))
//        .andExpect(status().is2xxSuccessful());
//    }

    @Test
    void deleteUser() {
    }

    @Test
    void givenUserId_whenUserUpdate_thenUserChangeStatus() {
    }
}