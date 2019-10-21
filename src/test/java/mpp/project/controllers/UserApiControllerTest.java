package mpp.project.controllers;

import mpp.project.Services.UserServices;
import mpp.project.model.User;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserApiController.class)
public class UserApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    public UserServices userServices() {
        return new UserServices();
    }

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
            //    .apply(springSecurity())
                .build();
    }

    @AfterEach
    void tearDown() {
    }



//    @Test
//  public  void signUp() throws Exception {
//
//
////        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/signup")
////                .contentType(MediaType.APPLICATION_JSON))
////                //.content("{ \"userName\": \"tamir\", \"userpassword\" : \"123\", \"email\" : \"tamir@sad\" }")
////                        .andExpect(status().isOk())
////                        .andReturn()
////                //.accept(MediaType.TEXT_PLAIN))
////                //.andExpect(content().string("SUCCESS")
////                //.andExpect(status().isOk())
////                ;
//
//    }

//    @Test
//    public void updateUser() {
//    }
//
//    @Test
//   public void signIn() {
//    }
//
//
//
//    @Test
//    public void getUserFollowers() {
//    }
//
//    @Test
//    public void getUserFollowing() {
//    }
//
//    @Test
//    public void getUser() {
//    }
//
//    @Test
//    public void setUserFollow() {
//    }
//
//    @Test
//    public void setUserUnFollow() {
//    }
}