package com.team.recipe.domain.user.dto;

import com.team.recipe.domain.user.domain.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class AddUserRequest {
    private String userId;
    private String password;
    private String userName;
    private String userEmail;

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .password(password)
                .userName(userName)
                .userEmail(userEmail)
                .build();
    }
}
