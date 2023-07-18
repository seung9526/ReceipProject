package com.team.recipe.domain.user.application.service;

import com.team.recipe.domain.user.domain.entity.RecipePost;
import com.team.recipe.domain.user.dto.RecipePostRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecipePostService {
    RecipePostRequest createRecipePost(RecipePostRequest postDto);
    RecipePostRequest getRecipePostById(long id);
    RecipePostRequest update(RecipePostRequest postDto, long id);
    void deleteRecipePostById(long id);

    Page<RecipePost> findAllByOrderByCreatedDateDescPageable(Pageable pageable);

}
