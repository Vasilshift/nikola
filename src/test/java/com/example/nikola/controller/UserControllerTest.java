package com.example.nikola.controller;

import com.example.nikola.model.User;
import com.example.nikola.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public  class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    User user1 = new User("John");
    User user2 = new User("Sasha");

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenListOfUsers_whenGetAllUsers_thenReturnAllUsers() throws Exception {
        List<User> listUsers = new ArrayList<>();
        listUsers.add(user1);
        listUsers.add(user2);
        UserServiceImpl mock = org.mockito.Mockito.mock(UserServiceImpl.class);
        when(mock.getAllUsers()).thenReturn(listUsers);
        ResultActions response = mockMvc.perform(get("/api/v1/getusers"));
        response.andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void givenUser_whenCreateUser_thenReturnSaveUser() throws Exception {
        BDDMockito.given(userServiceImpl.saveUser(ArgumentMatchers.any(User.class)))
                .willAnswer(invocation -> invocation.getArgument(0));
        ResultActions perform = mockMvc.perform(post("/api/v1/adduser")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user1)));
        perform.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name",
                        CoreMatchers.is(user1.getName())));
    }

    @Test
    void givenUserId_whenGetUser_thenReturnUser() throws Exception {
        userServiceImpl.saveUser(user1);
        Long userId = user1.getId();
        when(userServiceImpl.getUser(userId)).thenReturn(user1);
        ResultActions perform = mockMvc.perform(get("/api/v1/getusers/{id}", userId));
        perform.andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteUser() {
    }

    @Test
    void givenUserId_whenUserUpdate_thenUserChangeStatus() {
    }
}