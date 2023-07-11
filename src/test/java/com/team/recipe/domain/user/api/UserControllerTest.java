package com.team.recipe.domain.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.recipe.domain.user.application.service.UserService;
import com.team.recipe.domain.user.dao.repository.UserRepository;
import com.team.recipe.domain.user.domain.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private UserService userService;


    @Test
    @DisplayName("회원가입 성공")
    void registerUser() throws Exception {
        // given : 가입 요청에 필요한 데이터 설정
        User user = new User();
        user.setUserId("userTestId");
        user.setPassword("test");
        user.setUserName("testUser");
        user.setUserEmail("testEmail@test.com");

        userRepository.save(user);

        // when : 가입 요청 수행 및 검증
        ResultActions result = mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)));

        result.andExpect(status().isCreated());

        // than : 저장된 사용자의 수 확인
        List<User> users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getUserName()).isEqualTo("testUser");
    }

    @Test
    @DisplayName("회원 조회")
    void FindByUser() throws Exception {
        // given
        User user = new User();
        user.setUserId("userTestId");
        user.setPassword("test");
        user.setUserName("testUser");
        user.setUserEmail("testEmail@test.com");

        userRepository.save(user);

        // when
        ResultActions result = mockMvc.perform(get("/api/users/{id}", user.getUserId())
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());

        // then
        Optional<User> users = userRepository.findByUserName("testUser");
        assertThat(users.isPresent()).isTrue();
        assertThat(users.get().getUserName()).isEqualTo("testUser");
    }
}
