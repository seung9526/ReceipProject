package com.team.recipe.domain.user.dao.repository;

import com.team.recipe.domain.user.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String userName);
}