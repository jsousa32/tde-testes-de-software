package com.atividade.tde.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The Class SecurityConfiguration
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/06/2024
 */
@Configuration
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
