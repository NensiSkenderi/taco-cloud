package com.taco.cloud.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Custom user details store
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    // for securing web requests
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // The order of these rules is important.
        // Security rules declared first take precedence over those declared lower down
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()

                .and()
                .formLogin()
                .loginPage("/login")
                /*
                By default, a successful login will take
                the user directly to the page that they
                were navigating to when Spring Security determined
                that they needed to log in.
                But we can specify it
                 */
                //.defaultSuccessUrl("/design")
                .defaultSuccessUrl("/design", true) // force, even if they were navigating elsewhere

                .and()
                .logout()
                .logoutSuccessUrl("/")


                // Allow pages to be loaded in frames from the same origin; needed for H2-Console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin();

    }
}
