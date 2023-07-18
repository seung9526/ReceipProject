package com.team.recipe.domain.user.api;

import com.team.recipe.domain.user.application.service.UserService;
import com.team.recipe.domain.user.domain.entity.User;
import com.team.recipe.domain.user.dto.AddUserRequest;
import com.team.recipe.domain.user.dto.JwtAuthResponse;
import com.team.recipe.domain.user.dto.LoginRequest;
import com.team.recipe.domain.user.exception.ApiException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@Tag(
        name = "CRUD REST APIs for User Resource"
)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    // TODO: 회원가입 API
    @Operation(summary = "회원가입", description = "회원가입 진행")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "가입 성공"),
            @ApiResponse(responseCode = "400", description = "가입 실패")
    })
    @PostMapping({"/register", "/signup"})
    public ResponseEntity<User> register(@Valid @RequestBody AddUserRequest request) {

        User savedUser = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping({"/login", "/signin"})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginRequest loginRequest){
        String token = userService.login(loginRequest);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    // TODO : userID를 기반으로 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId){
        if(userId == null) {
            throw new ApiException("Post id cannot null", HttpStatus.NOT_FOUND);
        } else {
            userService.deleteUser(userId);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
