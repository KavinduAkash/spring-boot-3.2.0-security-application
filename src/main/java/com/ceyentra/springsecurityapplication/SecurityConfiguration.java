package com.ceyentra.springsecurityapplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(registry->{
           registry.requestMatchers("/home").permitAll();
           registry.requestMatchers("/admin/**").hasRole("ADMIN");
           registry.requestMatchers("/user/**").hasRole("USER");
           registry.anyRequest().authenticated();
        })
                .formLogin(formLogin->formLogin.permitAll())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails normalUser = User.builder().username("kavindu").password("$2a$12$u97O1jjBAOH4P.zDGip4SunPspGeWLpIRBosyh6MxrOQho1IyFLeO").roles("USER").build();
        UserDetails adminUser = User.builder().username("admin").password("$2a$12$u97O1jjBAOH4P.zDGip4SunPspGeWLpIRBosyh6MxrOQho1IyFLeO").roles("ADMIN", "USER").build();
        return new InMemoryUserDetailsManager(normalUser, adminUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
