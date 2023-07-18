package com.team.recipe.domain.user.dao.repository;

import com.team.recipe.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String userEmail);
    Optional<User> findByUserNameOrUserEmail(String userName, String userEmail);
    Optional<User> findByUserName(String userName);
    Boolean existsByUserName(String userName);
    Boolean existsByUserEmail(String userEmail);

    void deleteByUserId(String userID);
}
