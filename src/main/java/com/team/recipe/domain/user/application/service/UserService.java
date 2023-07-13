package com.team.recipe.domain.user.application.service;

import com.team.recipe.domain.user.domain.entity.User;
import com.team.recipe.domain.user.dto.AddUserRequest;
import com.team.recipe.domain.user.dto.LoginRequest;

public interface UserService {
    User register(AddUserRequest request);
    String login(LoginRequest loginDto);

    void deleteUser(long userID);
}
