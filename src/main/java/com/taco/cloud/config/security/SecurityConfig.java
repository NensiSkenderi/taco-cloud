package com.taco.cloud.config.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /* here we write code that uses the given AuthenticationManagerBuilder
        to specify how users will be looked up during authentication.
         */


        // In-memory
        auth.inMemoryAuthentication()
                    .withUser("nensi")
                    .password("nensi")
                    .authorities("ROLE_USER")
                .and()
                    .withUser("test")
                    .password("test")
                    .authorities("ROLE_USER");

        super.configure(auth);
    }
}
