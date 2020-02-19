package com.team6.serviceme.controller;

import com.team6.serviceme.domain.User;
import com.team6.serviceme.service.UserService;
import com.team6.serviceme.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(tags = "User Management")
@RestController
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

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

//    /**
//     * Logout
//     * @param session
//     * @return
//     */
//    @PostMapping("/user/logout")
//    @ApiOperation(value = "Logout")
//    public String logout(@RequestBody HttpSession session) throws AuthenticationException{
//        session.removeAttribute("success");
//        return "Logout Success";
//    }

    /**
     * get_question
     * @param user
     * @return
     */
    @PostMapping("/user/forget_get_question")
    @ApiOperation(value = "get_question")
    public String forgetGetQuestion(@Valid @RequestBody User user) throws AuthenticationException{
        return userService.selectQuestion(user);

    }

    /**
     * check_answer
     * @param user
     * @return
     */
    @PostMapping("/user/forget_check_answer")
    @ApiOperation(value = "check_answer")
    public String forgetCheckAnswer(@Valid @RequestBody User user) throws AuthenticationException{
        return userService.checkAnswer(user);
    }

    /**
     * reset_password
     * @param user
     * @return
     */
    @PostMapping("/user/forget_reset_password")
    @ApiOperation(value = "reset_password")
    public String forgetResetPassword(@Valid @RequestBody User user) throws AuthenticationException{
        return userService.resetPassword(user);
    }

    /**
     * reset_password
     * @param user
     * @return
     */
    @PostMapping("/user/reset_password")
    @ApiOperation(value = "reset_password")
    public String resetPassword(@Valid @RequestBody User user, HttpServletRequest request) throws AuthenticationException{
        String authorization = request.getHeader("authorization");
        if(authorization == null){
            //返回登陆
            return "Need to login";
        }
        try{
            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            if(jwtTokenUtil.validateToken(authorization, userDetails)){
                return userService.resetPassword(user);
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Token invalid";
        }

        return "reset password failed";
    }
}
