package com.team6.serviceme.repository;

import com.team6.serviceme.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * JPA Auto SQL
     * Use Username Find User
     */
    User findUserByUserName(String username);
}
