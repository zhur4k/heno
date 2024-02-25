package com.heno.config;

import com.heno.service.UserUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final UserUserDetailsService userService;

    public SecurityConfiguration(UserUserDetailsService userService) {
        this.userService = userService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/employees/**",
                                "/agreements/sale/**",
                                "/agreements/supply/**",
                                "/currencies/**",
                                "/partners/**",
                                "/products/**",
                                "/units/**"

                        ).hasAnyAuthority("SUPERVISOR")

                        .requestMatchers(
                                "/agreements/sale/**",
                                "/agreements/supply/**",
                                "/currencies/**",
                                "/partners/**",
                                "/products/**",
                                "/units/**"

                        ).hasAnyAuthority("LOGISTICALSUPERVISOR")

                        .requestMatchers(
                                "/"
                        ).hasAnyAuthority("ACCOUNTANT")

                        .requestMatchers(
                                "/agreements/sale/**",
                                "/currencies/**",
                                "/partners/**",
                                "/products/**",
                                "/units/**"
                        ).hasAnyAuthority("SALESMAN")

                        .requestMatchers(
                                "/agreements/supply/**",
                                "/currencies/**",
                                "/partners/**",
                                "/products/**",
                                "/units/**"

                        ).hasAnyAuthority("LOGISTICAL")

                        .requestMatchers(
                                "/css/**",
                                "/images/**",
                                "/js/**"
                        ).permitAll()

                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        return authenticationManagerBuilder.build();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }
        @Bean
        public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
//            return new BCryptPasswordEncoder();
        }
}
