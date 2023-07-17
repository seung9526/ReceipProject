package com.team.recipe.domain.user.application.service.impl;

import com.team.recipe.domain.user.application.service.RecipePostService;
import com.team.recipe.domain.user.dao.repository.RecipePostRepository;
import com.team.recipe.domain.user.domain.entity.RecipePost;
import com.team.recipe.domain.user.dto.RecipePostRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RecipePostServiceImpl implements RecipePostService {

    private final RecipePostRepository recipePostRepository;
    private final ModelMapper mapper;

    public RecipePostServiceImpl(RecipePostRepository recipePostRepository, ModelMapper mapper) {
        this.recipePostRepository = recipePostRepository;
        this.mapper = mapper;
    }


    @Override
    public RecipePostRequest createRecipePost(RecipePostRequest postDto) {

        // convert DTO to entity
        RecipePost post = mapToEntity(postDto);
        RecipePost newPost = recipePostRepository.save(post);

        // convert entity to DTO
        RecipePostRequest postResponse = mapToDTO(newPost);
        return postResponse;
    }

    @Override
    public RecipePostRequest getRecipePostById(long id) {
        return null;
    }

    @Override
    public RecipePostRequest update(RecipePostRequest postDto, long id) {
        return null;
    }

    @Override
    public void deleteRecipePostById(long id) {

    }

    private RecipePostRequest mapToDTO(RecipePost post) {
        RecipePostRequest recipePostDTO = mapper.map(post, RecipePostRequest.class);
        return recipePostDTO;
    }
    private RecipePost mapToEntity(RecipePostRequest postDto) {
        RecipePost recipePost = mapper.map(postDto, RecipePost.class);
        recipePost.getUser();
        return recipePost;
    }
}
