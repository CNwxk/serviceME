package com.team6.serviceme.controller;

import com.team6.serviceme.domain.User;
import com.team6.serviceme.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpSession;
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
     * @param session
     * @return
     */
    @PostMapping("/user/login")
    @ApiOperation(value = "Login")
    public String login(@Valid @RequestBody User user,  HttpSession session) throws AuthenticationException {
        String response = userService.login(user);
        if(response != null){
            session.setAttribute("currentUser", user);
        }
        return response;

    }

    /**
     * Logout
     * @param session
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "Logout")
    public String logout(@RequestBody HttpSession session) throws AuthenticationException{
        session.removeAttribute("currentUser");
        return "Logout Success";
    }

    /**
     * get_question
     * @param username
     * @return
     */
    @PostMapping("/forget_get_question")
    @ApiOperation(value = "get_question")
    public String forgetGetQuestion(@Valid @RequestBody String username) throws AuthenticationException{
        return userService.selectQuestion(username);

    }

    /**
     * check_answer
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @PostMapping("/forget_check_answer")
    @ApiOperation(value = "check_answer")
    public String forgetCheckAnswer(@Valid @RequestBody String username,String question,String answer) throws AuthenticationException{
        return userService.checkAnswer(username,question,answer);
    }

    /**
     * reset_password
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    @PostMapping("/forget_reset_password")
    @ApiOperation(value = "reset_password")
    public String forgetResetPassword(@Valid @RequestBody String username,String passwordNew,String forgetToken) throws AuthenticationException{
        return userService.resetPassword(username, passwordNew, forgetToken);
    }

    /**
     * reset_password
     * @param session
     * @param passwordOld
     * @param passwordNew
     * @return
     */
    @PostMapping("/reset_password")
    @ApiOperation(value = "reset_password")
    public String resetPassword(@Valid @RequestBody HttpSession session,String passwordOld,String passwordNew) throws AuthenticationException{
        User user = (User)session.getAttribute("currentUser");
        if(user == null){
            return "User is not login";
        }
        return userService.loginResetPassword(passwordOld,passwordNew,user);
    }
}
