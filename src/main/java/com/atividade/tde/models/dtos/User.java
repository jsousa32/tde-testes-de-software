package com.atividade.tde.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

/**
 * The Class User
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/06/2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NotBlank(message = "nome obrigatório")
    @Size(min = 3, max = 50, message = "nome máximo 50 caracteres")
    private String firstName;

    @NotBlank(message = "nome obrigatório")
    @Size(min = 3, max = 50, message = "máximo 50 caracteres")
    private String lastName;

    @NotBlank(message = "email obrigatório")
    @Email(message = "email está inválido")
    @Size(max = 100, message = "email máximo de 100 caracteres")
    private String email;

    @NotBlank(message = "cpf obrigatório")
    @CPF(message = "cpf está inválido")
    @Pattern(regexp = "\\d{11}", message = "cpf pode conter apenas números")
    @Size(min = 11, max = 11, message = "cpf tamanho de 11 caracteres")
    private String document;

    @NotBlank(message = "senha obrigatória")
    @Size(min = 3, max = 50, message = "senha máximo de 50 caracteres")
    private String password;
}
