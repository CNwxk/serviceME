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

    @Override
    public String login(User user){
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(
                user.getUserName(), user.getPassWord());

        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public String selectQuestion(User user){
        String username = user.getUserName();
        User u = userRepository.findQuestionByUserName(username);
        if(u.getQuestion() != null){
            return u.getQuestion();
        }
        return "The question of getting back the password is empty";
    }

    @Override
    public String checkAnswer(User user){
        String username = user.getUserName();
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
        String username = user.getUserName();
        String passwordNew = user.getPassWord();
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

}
