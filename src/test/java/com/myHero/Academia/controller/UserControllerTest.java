package com.myHero.Academia.controller;

import com.myHero.Academia.config.JwtUtil;
import com.myHero.Academia.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest (UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserService userService;

    //=== hello test ===//
    @Test
    public void helloWorld_ReturnsString_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/hello")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!!"));
    }

    //=== custom method ===//
    private static String createDummyUserInJson(String name, String password) {
        return "{  \"name\" : \"" + name + "\"," +
                "\"password\" : \"" + password + "\"}";
    }

    //=== login test ===//
    @Test
    public void login_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createDummyUserInJson("deku", "quirky"));

        when(userService.login(any())).thenReturn("oneforall"); //test passes
        //when(userService.login(any())).thenReturn("noquirk"); //test meant to fail

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\" : \"oneforall\"}"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    //=== signup test ===//
    @Test
    public void signup_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createDummyUserInJson("bakugo", "hero"));

        when(userService.createUser(any())).thenReturn("0987"); //test passes
        //when(userService.createUser(any())).thenReturn("000"); //test meant to fail

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\" : \"0987\"}"))
                .andReturn();


        System.out.println(result.getResponse().getContentAsString());
    }

}
