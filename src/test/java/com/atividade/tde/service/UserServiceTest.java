package com.atividade.tde.service;

import com.atividade.tde.exceptions.GlobalException;
import com.atividade.tde.models.dtos.User;
import com.atividade.tde.models.entities.UserEntity;
import com.atividade.tde.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should return exception when email already use")
    public void shouldReturnThrowWhenEmailAlreadyUse() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        GlobalException exception = assertThrows(GlobalException.class, () -> {
            userService.save(user);
        });

        assertEquals("Email already use", exception.getMessage());

        assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());

        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("should return exception when document already use")
    public void shouldReturnThrowWhenDocumentAlreadyUse() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);

        when(userRepository.existsByDocument(user.getDocument())).thenReturn(true);

        GlobalException exception = assertThrows(GlobalException.class, () -> {
            userService.save(user);
        });

        assertEquals("Document already use", exception.getMessage());

        assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());

        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("should save user when email and document isn't already use")
    public void shoulSaveWhenEmailAndDocumentNotAlreadyUse() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);

        when(userRepository.existsByDocument(user.getDocument())).thenReturn(false);

        when(user.getPassword()).thenReturn("12345");

        userService.save(user);

        verify(userRepository, times(1)).save(any(UserEntity.class));
    }
}