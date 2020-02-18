package com.team6.serviceme.repository;

import com.team6.serviceme.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * JPA Auto SQL
     * Use Username Find User
     */
    User findUserByUserName(String username);

    User findQuestionByUserName(String username);

    User findByUserNameAndQuestionAndAnswer(String username, String question, String answer);

    User updatePasswordByUserName(String username, String passWord);

    User findByPassWordAndId(String password, Long id);

    User updateById(Long id, String password);

}
