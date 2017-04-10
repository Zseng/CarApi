package com.zseng.car.configuration;

import com.zseng.car.common.PasswordHashEncoder;
import com.zseng.car.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by cc on 16/6/10.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Configuration
    @Order(1)
    public static class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        UserAuthService userAuthService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.antMatcher("/api/**")
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET).permitAll()
                    .antMatchers(HttpMethod.POST).authenticated()
                    .anyRequest().authenticated()
                    .and()
                    .csrf().disable()
                    .httpBasic();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userAuthService)
                    .passwordEncoder(new PasswordHashEncoder());
        }
    }

}
