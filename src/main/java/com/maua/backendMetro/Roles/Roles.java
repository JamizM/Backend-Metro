package com.maua.backendMetro.Roles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Roles {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails administrator = User.builder()
                .username("Admin")
                .password(passwordEncoder().encode("Admin"))
                .roles("EMPLOYEE","MANAGER","ADMINISTRATOR")
                .build();

        UserDetails employee = User.builder()
                .username("Employee")
                .password(passwordEncoder().encode("Employee"))
                .roles("EMPLOYEE")
                .build();
        UserDetails maintenance = User.builder()
                .username("Maintenance")
                .password(passwordEncoder().encode("Maintenance"))
                .roles("MAINTENANCE")
                .build();
        UserDetails user = User.builder()
                .username("User")
                .password(passwordEncoder().encode("User"))
                .roles("USER")
                .build();


        return  new InMemoryUserDetailsManager(administrator,employee,maintenance,user);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET,"/api/Users").hasAnyRole("EMPLOYEE","USER","ADMINISTRATOR","MAINTENANCE")
                        .requestMatchers(HttpMethod.GET,"/api/Users/**").hasAnyRole("EMPLOYEE","ADMINISTRATOR","USER","MAINTENANCE")
                        .requestMatchers(HttpMethod.POST,"/api/Users").hasAnyRole("ADMINISTRATOR","MAINTENANCE")
                        .requestMatchers(HttpMethod.DELETE, "/api/Users/**").hasAnyRole("ADMINISTRATOR","MAINTENANCE")
                        .requestMatchers(HttpMethod.PUT,"/api/Users/**").hasAnyRole("ADMINISTRATOR","MAINTENANCE")
                        .requestMatchers(HttpMethod.GET,"/api/Localizations").hasAnyRole("EMPLOYEE","USER","ADMINISTRATOR","MAINTENANCE")
                        .requestMatchers(HttpMethod.GET,"/api/Localizations/**").hasAnyRole("EMPLOYEE","ADMINISTRATOR","USER","MAINTENANCE")
                        .requestMatchers(HttpMethod.POST,"/api/Localizations").hasAnyRole("ADMINISTRATOR","MAINTENANCE")
                        .requestMatchers(HttpMethod.DELETE, "/api/Localizations/**").hasAnyRole("ADMINISTRATOR","MAINTENANCE")
                        .requestMatchers(HttpMethod.PUT,"/api/Localizations/**").hasAnyRole("ADMINISTRATOR","EMPLOYEE","MAINTENANCE")
                        .requestMatchers(HttpMethod.GET,"/api/HistoricManutentions").hasAnyRole("EMPLOYEE","USER","ADMINISTRATOR","MAINTENANCE")
                        .requestMatchers(HttpMethod.GET,"/api/HistoricManutentions/**").hasAnyRole("EMPLOYEE","ADMINISTRATOR","USER","MAINTENANCE")
                        .requestMatchers(HttpMethod.POST,"/api/HistoricManutentions").hasAnyRole("ADMINISTRATOR","MAINTENANCE")
                        .requestMatchers(HttpMethod.DELETE, "/api/HistoricManutentions/**").hasAnyRole("ADMINISTRATOR","MAINTENANCE")
                        .requestMatchers(HttpMethod.PUT,"/api/HistoricManutentions/**").hasAnyRole("ADMINISTRATOR","EMPLOYEE","MAINTENANCE")
                        .requestMatchers(HttpMethod.GET,"/api/Extinguishers").hasAnyRole("EMPLOYEE","USER","ADMINISTRATOR","MAINTENANCE")
                        .requestMatchers(HttpMethod.GET,"/api/Extinguishers/**").hasAnyRole("EMPLOYEE","ADMINISTRATOR","USER","MAINTENANCE")
                        .requestMatchers(HttpMethod.POST,"/api/Extinguishers").hasAnyRole("ADMINISTRATOR","MAINTENANCE")
                        .requestMatchers(HttpMethod.DELETE, "/api/Extinguishers/**").hasAnyRole("ADMINISTRATOR","MAINTENANCE")
                        .requestMatchers(HttpMethod.PUT,"/api/Extinguishers/**").hasAnyRole("ADMINISTRATOR","EMPLOYEE","MAINTENANCE")
                        .requestMatchers("/api/h2-console/**").permitAll().anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // Para testes (sem criptografia)
    }

}
