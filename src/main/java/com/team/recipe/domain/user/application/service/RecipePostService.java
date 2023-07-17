package com.team.recipe.domain.user.application.service;

import com.team.recipe.domain.user.dto.RecipePostRequest;

import java.util.List;

public interface RecipePostService {
    RecipePostRequest createRecipePost(RecipePostRequest postDto);
    RecipePostRequest getRecipePostById(long id);
    RecipePostRequest update(RecipePostRequest postDto, long id);
    void deleteRecipePostById(long id);

}
