package ru.gb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    // переопределение фильтра filterChain
    SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws  Exception{
        httpSecurity.
                authorizeHttpRequests((authorize ) -> authorize
                        .requestMatchers("/css/**", "/favicon.ico", "/", "/home").permitAll()
                        .requestMatchers("/user").hasAnyRole("USER")
                        .requestMatchers("/admin").hasAnyRole("ADMIN")
                        .requestMatchers("/private-page").hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login.defaultSuccessUrl("/").permitAll()).
                logout(logout ->logout.logoutSuccessUrl("/")).exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedPage("/login"));
                return httpSecurity.build();
    }
    // переопределение кодировки пароля
    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    // переопределение пароля для админа и для пользователя
    @Bean
    UserDetailsManager userDetailsManager (){
        var user1 = User.withUsername("user").password("{noop}password").roles("USER").build();
        var user2 = User.withUsername("admin").password("{noop}password").roles("USER").build();
        return new InMemoryUserDetailsManager(user1,user2);
    }
}



