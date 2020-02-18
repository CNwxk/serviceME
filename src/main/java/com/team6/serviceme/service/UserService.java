package com.team6.serviceme.service;

import com.team6.serviceme.domain.User;

public interface UserService {
    /**
     * Register
     * @param user
     */
    User register(User user);

    /**
     * Login
     * @param user
     */
    String login(User user);

    /**
     * selectQuestion
     * @param username
     */
    String selectQuestion(String username);

    /**
     * checkAnswer
     * @param username
     * @param question
     * @param answer
     */
    String checkAnswer(String username,String question, String answer);

    /**
     * resetPassword
     * @param username
     * @param passwordNew
     * @param forgetToken
     */
    String resetPassword(String username, String passwordNew, String forgetToken);

    /**
     * loginResetPassword
     * @param passwordOld
     * @param passwordNew
     * @param user
     */
    String loginResetPassword(String passwordOld,String passwordNew,User user);
}
