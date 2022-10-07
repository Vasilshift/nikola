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
import static org.hamcrest.CoreMatchers.is;
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
        List<User> listUsers = new ArrayList<>();
        listUsers.add(user1);
        listUsers.add(user2);

        given(userService.getAllUsers()).willReturn(listUsers);

        mockMvc.perform(get("/api/v1/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",
                        is(listUsers.size())));
    }

    @Test
    void givenUser_whenCreateUser_thenReturnSavedUser() throws Exception {
        given(userService.createNewUser(ArgumentMatchers.any(User.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        ResultActions actions = mockMvc.perform(post("/api/v1/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user1)));

        actions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",
                        CoreMatchers.is(user1.getName())));
    }

    @Test
    void givenUserId_whenGetUser_thenReturnUser() throws Exception {
        Long userId = user1.getId();
        when(userService.getUser(userId)).thenReturn(user1);
        ResultActions perform = mockMvc.perform(get("/api/v1/users/{id}", userId));
        perform.andExpect(status().isOk());
    }

    @Test
    void givenUserId_whenDeleteUser_then_Return200() throws Exception {
        Long userId = user1.getId();
        willDoNothing().given(userService).deleteUser(userId);
        ResultActions response = mockMvc.perform(delete("/api/v1/users/{id}", userId));
        response.andExpect(status().isMovedPermanently())
                .andDo(print());
    }

    @Test
    void givenUserId_whenUserUpdate_thenUserChangeStatus() throws Exception {
        Long userId = user1.getId();
        user1.setState("UPDATED");

        given(userService.getUser(userId)).willReturn(user1);

        ResultActions response = mockMvc.perform(put("/api/v1/users/{id}", userId)
               .contentType("application/json")
               .content(objectMapper.writeValueAsString(user1)));

        response.andExpect(status().isOk())
                .andDo(print());
    }
}