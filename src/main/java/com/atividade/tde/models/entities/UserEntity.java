package com.atividade.tde.models.entities;

import com.atividade.tde.models.dtos.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.UUID;

/**
 * The Class Users
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/06/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 11, nullable = false, unique = true)
    private String document;

    @Column(nullable = false)
    private String password;

    public UserEntity(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.document = user.getDocument();
        this.password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
    }
}
