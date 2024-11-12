package com.example.hospital.Config;


import com.example.hospital.Filter.JwtTokenFilter;
import com.example.hospital.Service.impl.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@RequiredArgsConstructor
public class SecurityConfig {


    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)

                .authorizeHttpRequests(requests -> {
                    requests
                            .requestMatchers(
                                    "/**"
                            )
                            .permitAll()
                           /* .requestMatchers(GET,"hospital/users").hasAnyRole("ADMIN")
                            .requestMatchers(POST,"hospital/users/admin").hasAnyRole("ADMIN")
                            .requestMatchers(POST,"hospital/users/doctor").hasAnyRole("ADMIN")
                            .requestMatchers(POST,"hospital/users/receptionist").hasAnyRole("ADMIN")
                            .requestMatchers(POST,"hospital/users/receptionist/appointment").hasAnyRole("ADMIN")
                            .requestMatchers(POST,"hospital/major").hasAnyRole("ADMIN")
                            .requestMatchers(POST,"hospital/major/service").hasAnyRole("ADMIN")
                            .requestMatchers(POST,"hospital/user/appointment").hasAnyRole("RECEPTIONIST")*/
                            .anyRequest().authenticated();

                });
        return http.build();
    }

}