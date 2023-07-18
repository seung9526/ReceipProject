package com.team.recipe.domain.user.application.service.impl;

import com.team.recipe.domain.user.application.service.UserService;
import com.team.recipe.domain.user.dao.repository.AuthorityRepository;
import com.team.recipe.domain.user.dao.repository.UserRepository;
import com.team.recipe.domain.user.domain.entity.Role;
import com.team.recipe.domain.user.domain.entity.User;
import com.team.recipe.domain.user.dto.AddUserRequest;
import com.team.recipe.domain.user.dto.LoginRequest;
import com.team.recipe.domain.user.exception.ReceipAPIExceiption;
import com.team.recipe.domain.user.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService  {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final AuthorityRepository authorityRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtTokenProvider jwtTokenProvider;

    public User register(AddUserRequest request) {

        // TODO : add check for username exists in database
        if(userRepository.existsByUserName(request.getUserName())){
            throw new ReceipAPIExceiption(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }

        // TODO : add check for email exists in database
        if(userRepository.existsByUserEmail(request.getUserEmail())){
            throw new ReceipAPIExceiption(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        User user = User.builder()
                .userId(request.getUserId())
                .userEmail(request.getUserEmail())
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        Set<Role> roles = new HashSet<>();
        Role userRole = authorityRepository.findByName("ROLE_USER");
        roles.add(userRole);
        user.setRoles(roles);

        log.debug("Created Information for User: {}");

        return userRepository.save(user);
    }


    @Override
    public String login(LoginRequest loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUserNameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Transactional
    @Override
    public void deleteUser(String userID) {
        userRepository.deleteByUserId(userID);

    }
}
