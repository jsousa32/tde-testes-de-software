package com.atividade.tde.controllers;

import com.atividade.tde.exceptions.ExceptionController;
import com.atividade.tde.models.dtos.User;
import com.atividade.tde.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class UserController
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/06/2024
 */
@RestController
@RequestMapping("v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content =
                    {@Content(schema = @Schema(implementation = ExceptionController.ErrorHandling.class))}),
            @ApiResponse(responseCode = "409", description = "Conflict", content =
                    {@Content(schema = @Schema(implementation = ExceptionController.ErrorHandling.class))}),
    })
    @Operation(summary = "Endpoint responsável por cadastrar o usuário")
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid User user) {
        userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
