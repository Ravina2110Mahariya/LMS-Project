package com.LMS.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.LMS.Security.JwtFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {

        http.csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )

            .authorizeHttpRequests(auth -> auth
            		.requestMatchers(
            				
            	            "/",
            	            "/login",
            	            "/register",
            	            "/css/**",
            	            "/js/**",
            	            "/images/**"
            	    ).permitAll()

                // ================= PUBLIC =================
                .requestMatchers("/auth/**").permitAll()
                
                .requestMatchers("/otp/**")
                .permitAll()

                // ================= SWAGGER =================
                .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                ).permitAll()

                // ================= FILES =================
                .requestMatchers("/uploads/**").permitAll()
                .requestMatchers("/content/download/**").permitAll()

                // ================= USERS =================
                .requestMatchers(
                        "/api/users/login",
                        "/api/users/register",
                        "/api/users/forgot-password",
                        "/api/users/reset-password"
                ).permitAll()

                .requestMatchers("/api/users/logout")
                .authenticated()

                .requestMatchers("/api/users/**")
                .hasRole("ADMIN")

                // ================= COURSES =================
                .requestMatchers(HttpMethod.GET, "/courses/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/courses/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/courses/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/courses/**").hasRole("ADMIN")

                // ================= CONTENT =================
                .requestMatchers(HttpMethod.GET, "/content/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/content/**").hasRole("ADMIN")

                // ================= ENROLL =================
                .requestMatchers("/enroll/**").hasAnyRole("STUDENT", "ADMIN")

                // ================= PROGRESS =================
                .requestMatchers("/progress/**").hasAnyRole("STUDENT", "ADMIN")

                // ================= CERTIFICATE =================
                .requestMatchers("/certificate/**").hasAnyRole("STUDENT", "ADMIN")

                // ================= ASSIGNMENT =================
                .requestMatchers(HttpMethod.GET, "/assignment/**").hasAnyRole("STUDENT", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/assignment/**").hasAnyRole("STUDENT", "ADMIN")

                // ================= SUBMISSION =================
                .requestMatchers(HttpMethod.POST, "/submission/submit").hasRole("STUDENT")
                .requestMatchers(HttpMethod.GET, "/submission/my").hasRole("STUDENT")
                .requestMatchers(HttpMethod.GET, "/submission/assignment/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/submission/marks/**").hasRole("ADMIN")

                // ================= QUIZ =================
                .requestMatchers(HttpMethod.GET, "/quiz/**").hasAnyRole("ADMIN", "STUDENT")
                .requestMatchers(HttpMethod.POST, "/quiz/add").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/quiz/submit").hasRole("STUDENT")
                .requestMatchers(HttpMethod.GET, "/quiz/my-result").hasRole("STUDENT")

                // ================= STUDENT =================
                //.requestMatchers("/student/**").hasRole("STUDENT")//
                
                .requestMatchers("/student/**").permitAll()

                // ================= ADMIN =================
                .requestMatchers("/admin/**").hasRole("ADMIN")

                // ================= REVIEWS =================
                .requestMatchers(HttpMethod.GET, "/reviews/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/reviews/**").hasAnyRole("STUDENT", "ADMIN")

                // ================= DISCUSSION =================
                .requestMatchers(HttpMethod.POST, "/discussion/ask").hasRole("STUDENT")
                .requestMatchers(HttpMethod.GET, "/discussion/**").hasAnyRole("STUDENT", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/discussion/reply/**").hasRole("ADMIN")
                
             // ================= VIDEO =================

                .requestMatchers(HttpMethod.POST, "/video/upload" ).hasRole("ADMIN")

                .requestMatchers( HttpMethod.GET, "/video/**" ).hasAnyRole("ADMIN", "STUDENT")

                // ================= NOTIFICATION =================
                .requestMatchers(HttpMethod.POST, "/notification/send").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/notification/my").hasRole("STUDENT")
                .requestMatchers(HttpMethod.GET, "/notification/all").hasRole("ADMIN")
                
             // ================= ATTENDANCE =================

                .requestMatchers(HttpMethod.POST,"/attendance/mark").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/attendance/my").hasRole("STUDENT")

                .requestMatchers(HttpMethod.GET,"/attendance/course/**").hasRole("ADMIN")
                
             // ================= LIVE CLASS =================

                .requestMatchers(HttpMethod.POST,"/live/create").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET,"/live/**").hasAnyRole("ADMIN", "STUDENT")
                  
             // ================= NOTES =================

                .requestMatchers(HttpMethod.POST,"/notes/upload").hasRole("ADMIN")

                .requestMatchers( HttpMethod.GET, "/notes/**").hasAnyRole("ADMIN", "STUDENT")
                
             // ================= CHAT =================

                .requestMatchers(HttpMethod.POST, "/chat/send").hasAnyRole("ADMIN", "STUDENT")

                .requestMatchers(HttpMethod.GET,"/chat/**").hasAnyRole("ADMIN", "STUDENT")
                
                // ================= DASHBOARD =================
                .requestMatchers("/dashboard/**").hasAnyRole("STUDENT", "ADMIN")

                // ================= DEFAULT =================
                .anyRequest().authenticated()
            )

            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ================= PASSWORD ENCODER =================
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}