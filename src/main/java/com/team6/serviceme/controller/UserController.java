package com.team6.serviceme.controller;

import com.team6.serviceme.base.BaseResponse;
import com.team6.serviceme.base.ResultResponse;
import com.team6.serviceme.domain.User;
import com.team6.serviceme.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;

import javax.validation.Valid;
import java.util.List;

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
    public BaseResponse<User> register(@Valid @RequestBody User user) throws AuthenticationException {
        return new ResultResponse<>(userService.register(user));
    }

    /**
     * Login
     * @param user
     * @return
     */
    @PostMapping("/user/login")
    @ApiOperation(value = "Login")
    public BaseResponse<List<String>> login(@Valid @RequestBody User user) throws AuthenticationException {
        return new ResultResponse<>(userService.login(user));
    }


    /**
     * get_user_question
     * @param user
     * @return
     */
    @PostMapping("/user/forget_get_question")
    @ApiOperation(value = "get_question")
    public BaseResponse<String> forgetGetQuestion(@Valid @RequestBody User user) throws AuthenticationException{
        return new ResultResponse<>(userService.selectQuestion(user));

    }

    /**
     * check_answer
     * @param user
     * @return
     */
    @PostMapping("/user/forget_check_answer")
    @ApiOperation(value = "check_answer")
    public BaseResponse<String> forgetCheckAnswer(@Valid @RequestBody User user) throws AuthenticationException{
        return new ResultResponse<>(userService.checkAnswer(user));
    }

    /**
     * reset_password
     * @param user
     * @return
     */
    @PostMapping("/user/forget_reset_password")
    @ApiOperation(value = "reset_password")
    public BaseResponse<String> forgetResetPassword(@Valid @RequestBody User user) throws AuthenticationException{
        return new ResultResponse<>(userService.resetPassword(user));
    }

    /**
     * login_reset_password
     * @param user
     * @return
     */
    @PostMapping("/login_reset_password")
    @ApiOperation(value = "login_reset_password")
    public BaseResponse<String> loginResetPassword(@Valid @RequestBody User user, Authentication auth) throws AuthenticationException{
        user.setId((Long)auth.getPrincipal());
        return new ResultResponse<>(userService.resetPassword(user));
    }

    /**
     * get_user_information
     * @param auth
     * @return
     */
    @PostMapping("/get_user_information")
    @ApiOperation(value = "get_user_information")
    public BaseResponse<User> getUserInformation(Authentication auth) throws AuthenticationException{
        User user = (User) auth.getPrincipal();
        return new ResultResponse<>(user);
    }

    /**
     * update_user_information
     * @param user
     * @param auth
     * @return
     */
    @PostMapping("/update_user_information")
    @ApiOperation(value = "update_user_information")
    public BaseResponse<User> updateUserInformation(@Valid @RequestBody User user, Authentication auth) throws AuthenticationException{
        user.setId((Long)auth.getPrincipal());
        return new ResultResponse<>(userService.updateInformation(user));   
    }
}
