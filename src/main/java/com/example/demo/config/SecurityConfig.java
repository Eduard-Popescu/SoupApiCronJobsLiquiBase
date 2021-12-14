package com.example.demo.config;

import com.example.demo.security.TokenValidatorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenValidatorFilter tokenValidatorFilter;

    @Autowired
    public SecurityConfig(TokenValidatorFilter tokenValidatorFilter) {
        this.tokenValidatorFilter = tokenValidatorFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
            .configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf()
            .disable()
            .addFilterBefore(tokenValidatorFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers("/api/login")
            .permitAll()
            .antMatchers("/api/person/findAll")
            .authenticated()
            .antMatchers("/api/person/findPerson")
            .authenticated()
            .antMatchers("/api/person/savePerson")
            .authenticated()
            .antMatchers("/api/person/getAll")
            .authenticated()
            .antMatchers("/api/person/getPerson")
            .authenticated()
            .antMatchers("/api/Wsdl/NumberToDollars")
            .authenticated()
            .antMatchers("/api/Wsdl/NumberToWords")
            .authenticated()
            .antMatchers("/api/posts/**")
            .authenticated()
            .and()
            .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
