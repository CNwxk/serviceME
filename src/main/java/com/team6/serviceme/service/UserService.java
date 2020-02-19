package com.team6.serviceme.service;

import com.team6.serviceme.domain.User;
import org.json.JSONObject;

public interface UserService {
    /**
     * Register
     * @param user
     */
    User register(User user);

    /**
     * Login
     * @param user
     * @return
     */
    String login(User user);

    /**
     * selectQuestion
     * @param user
     */
    String selectQuestion(User user);

    /**
     * checkAnswer
     * @param user
     */
    String checkAnswer(User user);

    /**
     * resetPassword
     * @param user
     */
    String resetPassword(User user);

}
