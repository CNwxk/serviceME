package com.team6.serviceme.service.impl;

import com.team6.serviceme.domain.User;
import com.team6.serviceme.repository.UserRepository;
import com.team6.serviceme.service.UserService;
import com.team6.serviceme.util.JwtTokenUtil;
import com.team6.serviceme.util.TokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public User register(User user){
        final String username = user.getUserName();
        if( userRepository.findUserByUserName(username)!=null ) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = user.getPassWord();
        user.setPassWord(encoder.encode(rawPassword));
        return userRepository.save(user);
    }

    public String login(User user){
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(
                user.getUserName(), user.getPassWord());

        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    public String selectQuestion(String username){
        User user = userRepository.findQuestionByUserName(username);
        if(user.getQuestion() != null){
            return user.getQuestion();
        }
        return "The question of getting back the password is empty";
    }

    public String checkAnswer(String username,String question, String answer){
        User user = userRepository.findByUserNameAndQuestionAndAnswer(username, question, answer);
        if(user != null){
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username,forgetToken);
            return forgetToken;
        }
        return "Wrong answer";
    }

    public String resetPassword(String username, String passwordNew, String forgetToken){
        if(forgetToken == null){
            return "Parameter error, token needs to be passed";
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if(token == null){
            return "Token is invalid or expired";
        }
        if(forgetToken.equals(token)){
            User user = userRepository.updatePasswordByUserName(username, passwordNew);
            if(user != null){
                return "password has been updated";
            }
        }else {
            return "Token error, please reobtain token for reset password";
        }
        return "Failed to change password";
    }

    public String loginResetPassword(String passwordOld,String passwordNew,User user){
        User u = userRepository.findByPassWordAndId(passwordOld, user.getId());
        if(u == null){
            return "Old password is wrong";
        }
        User us = userRepository.updateById(user.getId(), passwordNew);
        if(us != null){
            return "Password updated successfully";
        }
        return "Password updated failed";
    }

}
