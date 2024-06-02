package com.atividade.tde.repositories;

import com.atividade.tde.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * The Interface UsersRespository
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/06/2024
 */
@Transactional
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByEmail(@Param("email") String email);

    boolean existsByDocument(@Param("document") String document);
}
