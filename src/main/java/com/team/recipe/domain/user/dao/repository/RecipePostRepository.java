package com.team.recipe.domain.user.dao.repository;

import com.team.recipe.domain.user.domain.entity.RecipePost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipePostRepository extends JpaRepository<RecipePost, Long> {
}