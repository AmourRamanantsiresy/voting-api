package org.ambohipotsy.votingapp.controller.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private JwtAuthenticationFilter jwtAuthFilter;
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        e ->
                                e
                                        // ------------------------------- OTP MATCHER ------------------------------//
                                        .requestMatchers("/otp/whatIAm/{key}")
                                        .permitAll()
                                        .requestMatchers("/otp/generate/**")
                                        .authenticated()
                                        // --------------------------- VOTE ACTION MATCHER --------------------------//
                                        .requestMatchers("/candidate/{candidateId}")
                                        .authenticated()
                                        .requestMatchers("/vote/{key}/remove")
                                        .authenticated()
                                        .requestMatchers("/vote/{voteId}/make")
                                        .permitAll()
                                        .requestMatchers("/vote/{voteId}/makeWithKey")
                                        .permitAll()
                                        .requestMatchers("/vote/{voteId}/next")
                                        .permitAll()
                                        .requestMatchers("/vote/{voteId}/pv")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.GET, "/vote/{voteId}/voteSection**")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.GET, "/voteSection/{voteSectionId}/candidate**")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.GET, "/vote")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.GET, "/vote/{voteId}")
                                        .permitAll()
                                        // ------------------------- AUTHENTICATION MATCHER -------------------------//
                                        .requestMatchers("/auth/**")
                                        .permitAll()

                                        // ------------------------------ USER MATCHER ------------------------------//
                                        .requestMatchers("/user/whoami")
                                        .authenticated()
                                        .requestMatchers(new SelfMatcher(HttpMethod.GET, "/user/**"))
                                        .authenticated()
                                        .requestMatchers(new SelfMatcher(HttpMethod.PUT, "/user/**"))
                                        .authenticated()
                                        .requestMatchers("/**")
                                        .authenticated())
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(e -> e.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
