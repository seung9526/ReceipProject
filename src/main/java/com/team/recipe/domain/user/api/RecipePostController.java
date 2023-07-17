package com.team.recipe.domain.user.api;

import com.team.recipe.domain.user.application.service.RecipePostService;
import com.team.recipe.domain.user.dto.RecipePostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@Tag(
        name = "CRUD REST APIs for CPost Resource"
)
public class RecipePostController {

    private final RecipePostService postService;

    public RecipePostController(RecipePostService postService) {
        this.postService = postService;
    }

    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to save recipePost into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    // create blog post rest api
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<RecipePostRequest> createPost(@Valid @RequestBody RecipePostRequest postDto){
        return new ResponseEntity<>(postService.createRecipePost(postDto), HttpStatus.CREATED);
    }
}
