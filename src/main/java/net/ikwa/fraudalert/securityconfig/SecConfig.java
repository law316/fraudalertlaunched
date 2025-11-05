package net.ikwa.fraudalert.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecConfig {

            @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable()) // Disable CSRF (important for Postman/frontend to work)
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/registration/**","/api/login", "/api/login/**").permitAll()  // Allow ALL to registration endpoints
                            .anyRequest().permitAll()  // For now, allow everything else too. (You can lock this later)
                    );
            return http.build();
        }
        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }


}
