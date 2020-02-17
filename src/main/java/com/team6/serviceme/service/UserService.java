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
}
