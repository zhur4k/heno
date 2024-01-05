package com.heno.config;

import com.heno.service.UserUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

/**
 * This class configures the security settings for the application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    private UserUserDetailsService userUserDetailsService;
    private TokenFilter tokenFilter;

    /**
     * Sets the UserUserDetailsService bean.
     *
     * @param userUserDetailsService The UserService bean to be set.
     */
    @Autowired
    public void setUserUserDetailsService(UserUserDetailsService userUserDetailsService) {
        this.userUserDetailsService = userUserDetailsService;
    }

    /**
     * Sets the TokenFilter bean.
     *
     * @param tokenFilter The TokenFilter bean to be set.
     */
    @Autowired
    public void setTokenFilter(TokenFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    /**
     * Default constructor for SecurityConfiguration.
     */
    public SecurityConfiguration() {
    }

    /**
     * Configures the security filters for the application.
     *
     * @param http The HttpSecurity object to configure security.
     * @return A SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Disable CSRF protection for this application
                .csrf(AbstractHttpConfigurer::disable)

                // Configure CORS to permit all origins, methods, and headers
                .cors(httpSecurityCorsConfigurer ->
                        httpSecurityCorsConfigurer.configurationSource(request ->
                                new CorsConfiguration().applyPermitDefaultValues()))

                // Configure exception handling for unauthorized access
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))

                // Configure session management to be stateless
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Configure URL authorization rules
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/").permitAll()  // Permit all requests to the root endpoint
                        .anyRequest().permitAll()     // Permit all other requests
                )

                // Add custom token filter before the default UsernamePasswordAuthenticationFilter
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Configures the PasswordEncoder bean for encrypting passwords.
     *
     * @return A BCryptPasswordEncoder bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the primary AuthenticationManager bean.
     *
     * @param configuration The AuthenticationConfiguration to retrieve the authentication manager.
     * @return The configured AuthenticationManager.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    @Primary
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Configures the AuthenticationManagerBuilder with a custom DaoAuthenticationProvider.
     *
     * @param authenticationManagerBuilder The AuthenticationManagerBuilder to be configured.
     * @return The configured AuthenticationManagerBuilder.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    @Primary
    public AuthenticationManagerBuilder configureAuthenticationManagerBuilder(
            AuthenticationManagerBuilder authenticationManagerBuilder
    ) throws Exception {
        // Configure the authentication manager with the UserService and PasswordEncoder
        authenticationManagerBuilder.userDetailsService(userUserDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder;
    }
}
