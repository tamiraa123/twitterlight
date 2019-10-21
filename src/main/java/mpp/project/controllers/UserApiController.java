package mpp.project.controllers;

import mpp.project.Services.FollowService;
import mpp.project.Services.JwtUserDetailsService;
import mpp.project.Services.UserServices;
import mpp.project.config.JwtTokenUtil;
import mpp.project.model.JwtResponse;
import mpp.project.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.spring.web.json.Json;

import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
             }
        };
    }


    // Log declare here -- ene zaavl gj bichsen bsn
    Logger logger = LoggerFactory.getLogger(UserApiController.class);

    public UserApiController() {
    }

    @Autowired
    private UserServices userServices;

    @Autowired
    private FollowService followService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;


    @PostMapping (value = "/signup", consumes = "application/json", produces = "application/json")
    public String signUp(@RequestBody User u)
    {
        return userServices.AddUpdateUser(u);
    }

    @GetMapping(value = "/changepassword")
    public String updateUser(@RequestParam("newpassword") String newpassword,@RequestParam("userid") int userid) {
        if (userServices.SetPassword(userid, newpassword))
        {
            return "SUCCESS";
        }
        else {
            return "FAIL";
        }
    }




    @GetMapping(value = "/signin")
    public String signIn(@RequestParam("username") String userName,@RequestParam("userpassword") String userPassword) throws Exception {

        authenticate(userName, userPassword);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

        final String token = jwtTokenUtil.generateToken(userDetails);
        User newUser = new User();
        newUser = userServices.signIn(userName, userPassword);
        if (newUser.getIsActive() == 1)
        {
            String JSONSignIn = "{\"userid\":\""+ newUser.getUserid() +"\", \"token\":\""+token+"\"}";
            return JSONSignIn;
        }
        else {
            return "FAIL";
        }
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    @PostMapping (value = "/update", consumes = "application/json", produces = "application/json")
    public String updateUser(@RequestBody User u) {
        try
        {
            return userServices.AddUpdateUser(u);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }

       // return "500";
        return "FAIL";
    }

    @GetMapping(value = "/getfollowers")
    public List<User> getUserFollowers(@RequestParam("userid") int userid) {

        try
        {
            return followService.getFollowers(userid);

        }
        catch (Exception e) {
            logger.error(e.getMessage());

            List<User> vl = new ArrayList<>();
            return vl;
        }
    }

    @GetMapping(value = "/getfollowing")
    public List<User> getUserFollowing(@RequestParam("userid") int userid) {

        try
        {
           return  this.followService.getFollowing(userid);
        }
        catch (Exception e) {
            logger.error(e.getMessage());

            List<User> vl = new ArrayList<>();
            return vl;
        }
    }

    @GetMapping(value = "/user")
    public User getUser(@RequestParam("userid") int userid) {

        try
        {
            return userServices.getUserByID(userid);
        }
        catch (Exception e) {
            logger.error(e.getMessage());

            User u = new User();
            return u;
        }
    }

    @GetMapping(value = "/{userid}/follow/{followid}")
    public String setUserFollow(@PathVariable("userid") int userid,
                              @PathVariable("followid") int followid) {

        try
        {
            if(followService.setUserFollow(userid, followid))
            {
                return "Follow";
            }
            else
            {
                return "False";
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage());

             return "False";
        }
    }

    @GetMapping(value = "/{userid}/unfollow/{unfollowid}")
    public String setUserUnFollow(@PathVariable("userid") int userid,
                                @PathVariable("unfollowid") int unfollowid) {

        try
        {
            if(followService.setUserUnFollow(userid, unfollowid))
            {
                return "unFollow";
            }
            else
            {
                return "False";
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage());

            return "False";
        }
    }

}
