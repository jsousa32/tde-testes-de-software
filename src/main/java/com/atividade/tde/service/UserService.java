package com.atividade.tde.service;

import com.atividade.tde.exceptions.GlobalException;
import com.atividade.tde.models.dtos.User;
import com.atividade.tde.models.entities.UserEntity;
import com.atividade.tde.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * The Class UsersService
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/06/2024
 */
@Component
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new GlobalException("Email already use", HttpStatus.CONFLICT);
        }

        if (userRepository.existsByDocument(user.getDocument())) {
            throw new GlobalException("Document already use", HttpStatus.CONFLICT);
        }

        UserEntity userEntity = new UserEntity(user);

        userRepository.save(userEntity);
    }
}
