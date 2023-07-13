package com.team.recipe.domain.user.dao.repository;

import com.team.recipe.domain.user.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Role, Long> {
    Role findByName(String userName);
}