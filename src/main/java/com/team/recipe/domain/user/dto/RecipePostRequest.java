package com.team.recipe.domain.user.dto;

import com.team.recipe.domain.user.domain.entity.RecipePost;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class RecipePostRequest {

    private Long id;
    private String title;
    private String body;
    private Long userId;
    private String userName;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
    private String postDeleteYn;

    public RecipePostRequest () {}

    public RecipePostRequest(RecipePost recipePost){
        this.id = recipePost.getId();
        this.title = recipePost.getTitle();
        this.body = recipePost.getBody();
        this.createdBy = recipePost.getCreatedBy();
        this.createdDate = recipePost.getCreatedDate();
        this.lastModifiedBy = recipePost.getLastModifiedBy();
        this.lastModifiedDate = recipePost.getLastModifiedDate();
        this.userId = recipePost.getUser().getId();
        this.userName = recipePost.getUser().getUserName();
        this.postDeleteYn = recipePost.getPostDeleteYn();
    }


}
