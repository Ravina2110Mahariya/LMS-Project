package com.LMS.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.LMS.Security.CustomUserDetailsService;
import com.LMS.Security.JwtFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config)
            throws Exception {

        return config.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(
                customUserDetailsService
        );

        provider.setPasswordEncoder(
                passwordEncoder()
        );

        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http)
            throws Exception {

        http
            .authenticationProvider(
                    authenticationProvider()
            )
            .addFilterBefore(
                    jwtFilter,
                    UsernamePasswordAuthenticationFilter.class
            )


            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                    session.sessionCreationPolicy(
                            SessionCreationPolicy.IF_REQUIRED
                    )
            )

            .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl(
                            "/student/dashboard",
                            true
                    )
                    .permitAll()
            )

            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
            )

            .authorizeHttpRequests(auth -> auth

            		.requestMatchers(
            				"/favicon.ico",
            		        "/",
            		        "/login",
            		        "/register",
            		        "/auth/**",
            		        "/api/users/login",
            		        "/api/users/register",
            		        "/css/**",
            		        "/js/**",
            		        "/images/**"
            		).permitAll()

                    .requestMatchers(
                            HttpMethod.GET,
                            "/courses/**"
                    ).permitAll()

                    .requestMatchers("/student/**")
                    .hasRole("STUDENT")

                    .requestMatchers("/admin/**")
                    .hasRole("ADMIN")

                    .requestMatchers("/enroll/**")
                    .hasAnyRole(
                            "STUDENT",
                            "ADMIN"
                    )
                    
                    .requestMatchers("/content/download/**")
                    .hasAnyRole("STUDENT","ADMIN")

                    .requestMatchers("/content/**")
                    .hasRole("ADMIN")
                    
                    
                    .requestMatchers("/live/**")
                    .hasAnyRole("STUDENT","ADMIN")

                    .anyRequest()
                    .authenticated()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}