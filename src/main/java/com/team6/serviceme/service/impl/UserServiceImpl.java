package com.team6.serviceme.service.impl;

import com.team6.serviceme.domain.User;
import com.team6.serviceme.repository.UserRepository;
import com.team6.serviceme.service.UserService;
import com.team6.serviceme.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public User register(User user){
        final String username = user.getUsername();
        if( userRepository.findUserByUserName(username)!=null ) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = user.getPassword();
        user.setPassWord(encoder.encode(rawPassword));
        return userRepository.save(user);
    }

    @Override
    public List<String> login(User user){
        List list = new ArrayList();
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword());

        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(user.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        list.add(token);
        list.add(userDetails);
        return list;
    }

    @Override
    public String selectQuestion(User user){
        String username = user.getUsername();
        User u = userRepository.findQuestionByUserName(username);
        if(u.getQuestion() != null){
            return u.getQuestion();
        }
        return "The question of getting back the password is empty";
    }

    @Override
    public String checkAnswer(User user){
        String username = user.getUsername();
        String question = user.getQuestion();
        String answer = user.getAnswer();
        User u = userRepository.findByUserNameAndQuestionAndAnswer(username, question, answer);
        if(u != null){
            return "Success";
        }
        return "Wrong answer";
    }

    @Override
    public String resetPassword(User user){
        String passwordNew = user.getPassword();
        String username = user.getUsername();
        if( userRepository.findUserByUserName(username) != null){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            int result = userRepository.updatePassWordByUserName(encoder.encode(passwordNew), username);
            if(result > 0){
                return "password has been updated";
            }
        }else {
            return "username error";
        }
        return "Failed to change password";
    }

    @Override
    public User loginResetPassword(User user, User u){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        u.setPassWord(encoder.encode(user.getPassword()));
        return userRepository.save(u);
    }


    @Override
    public User updateInformation(User user, User u){
        u.setPhone(user.getPhone());
        u.setType(user.getType());
        u.setUserState(user.getUserState());
        u.setUserCity(user.getUserCity());
        u.setUserDetailAddress(user.getUserDetailAddress());
        u.setUserZipCode(user.getUserZipCode());
        u.setVendorDescription(user.getVendorDescription());
        u.setPhotoUrl(user.getPhotoUrl());

        return userRepository.save(u);
    }

}
