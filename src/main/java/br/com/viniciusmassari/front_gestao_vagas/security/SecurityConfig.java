package br.com.viniciusmassari.front_gestao_vagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    // private static final String[] PERMIT_ALL_LIST = {
    // "/candidate/login", "/candidate/signIn"
    // };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/candidate/login", "/candidate/signIn", "/candidate/create", "/company/create")
                    .permitAll();
            auth.anyRequest().authenticated();
        }).formLogin(form -> form.loginPage("/candidate/login"));

        return http.build();
    }
}
