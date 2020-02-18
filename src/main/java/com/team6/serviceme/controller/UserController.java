package com.team6.serviceme.controller;

import com.team6.serviceme.domain.User;
import com.team6.serviceme.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;
import javax.validation.Valid;

@Api(tags = "User Management")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Register
     * @param user
     * @return
     */
    @PostMapping("/user/register")
    @ApiOperation(value = "Register")
    public User register(@Valid @RequestBody User user) throws AuthenticationException {
        return userService.register(user);
    }

    /**
     * Login
     * @param user
     * @return
     */
    @PostMapping("/user/login")
    @ApiOperation(value = "Login")
    public String login(@Valid @RequestBody User user) throws AuthenticationException {
        return userService.login(user);
    }

}
