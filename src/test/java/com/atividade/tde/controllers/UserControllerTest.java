package com.atividade.tde.controllers;

import com.atividade.tde.exceptions.ExceptionController;
import com.atividade.tde.exceptions.GlobalException;
import com.atividade.tde.models.dtos.User;
import com.atividade.tde.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new ExceptionController())
                .build();
    }

    @Test
    @DisplayName("should return success when everything is ok")
    void shouldReturnSuccessWhenEverythingIsOk() throws Exception {
        User user = mockUser();

        doNothing().when(userService).save(user);

        mockMvc.perform(
                        post("/v1/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(user)))
                .andExpect(status().isCreated());

        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("should return conflict when email already use")
    void shouldReturnConflictWhenEmailAlreadyUse() throws Exception {
        User user = mockUser();

        doThrow(new GlobalException("Email already use", HttpStatus.CONFLICT))
                .when(userService).save(user);

        mockMvc.perform(
                        post("/v1/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(user)))
                .andExpect(status().isConflict());

        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("should return conflict when document already use")
    void shouldReturnConflictWhenDocumentAlreadyUse() throws Exception {
        User user = mockUser();

        doThrow(new GlobalException("Document already use", HttpStatus.CONFLICT))
                .when(userService).save(user);

        mockMvc.perform(
                        post("/v1/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(user)))
                .andExpect(status().isConflict());

        verify(userService, times(1)).save(any(User.class));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private User mockUser() {
        return new User().builder().firstName("João").lastName("Sousa").email("jsousa.quimica@gmail.com")
                .document("22111494054").password("12345").build();
    }
}